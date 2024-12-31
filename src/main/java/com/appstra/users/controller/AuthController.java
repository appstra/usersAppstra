package com.appstra.users.controller;

import com.appstra.users.config.JwtUtil;
import com.appstra.users.dto.LoginDTO;
import com.appstra.users.dto.ResponseLoginDTO;
import com.appstra.users.entity.User;
import com.appstra.users.methods.Apis;
import com.appstra.users.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.appstra.users.methods.Apis.GetApi;

@RestController
@RequestMapping("/api/v1/auntenticar")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @PostMapping("login")
    public ResponseEntity<ResponseLoginDTO> login(@RequestBody LoginDTO loginDTO) {
        // Autenticación del usuario
        Authentication authentication = authenticateUser(loginDTO);

        if (authentication.isAuthenticated()) {
            // Recuperación del usuario desde la base de datos
            User user = getUserFromDatabase(loginDTO.getUsername());

            // Generación del token y de la respuesta
            ResponseLoginDTO responseLoginDTO = generateLoginResponse(user);

            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, responseLoginDTO.getToken())
                    .body(responseLoginDTO);
        } else {
            return ResponseEntity.status(401).build(); // Unauthorized
        }
    }

    // Método para autenticar al usuario
    private Authentication authenticateUser(LoginDTO loginDTO) {
        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
        return this.authenticationManager.authenticate(login);
    }

    // Método para obtener el usuario desde la base de datos
    private User getUserFromDatabase(String username) {
        Optional<User> userOptional = userRepository.findByUserUser(username);
        return userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    // Método para generar la respuesta de login
    private ResponseLoginDTO generateLoginResponse(User user) {
        ResponseLoginDTO responseLoginDTO = new ResponseLoginDTO();

        String token = jwtUtil.create(user);
        responseLoginDTO.setToken(token);

        responseLoginDTO.setUser(Optional.of(user));

        // Llamada a la API externa para obtener la información adicional
        try {
            String responseGet = fetchExternalCompanyData(user);
            List<Map<String, Object>> companyList = parseCompanyData(responseGet);
            responseLoginDTO.setListCompany(companyList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        responseLoginDTO.setMessage("Logeo Correcto");
        return responseLoginDTO;
    }

    // Método para realizar la llamada a la API externa
    private String fetchExternalCompanyData(User user) throws IOException {
        String token = jwtUtil.create(user);
        String route = Apis.EMPLOYEE + user.getPerson().getPersonId();
        return GetApi("GET", route, token, null);
    }

    // Método para parsear los datos de la respuesta de la API externa
    private List<Map<String, Object>> parseCompanyData(String responseGet) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(responseGet, List.class);
    }
}
