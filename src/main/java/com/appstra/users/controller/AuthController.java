package com.appstra.users.controller;

import com.appstra.users.config.JwtUtil;
import com.appstra.users.dto.LoginDTO;
import com.appstra.users.dto.ResponseLoginDTO;
import com.appstra.users.entity.User;
import com.appstra.users.methods.Apis;
import com.appstra.users.methods.Fuctions;
import com.appstra.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

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

    private ResponseLoginDTO generateLoginResponse(User user) {
        Fuctions fuctions = new Fuctions();
        ResponseLoginDTO responseLoginDTO = new ResponseLoginDTO();

        // Generar token y establecer el usuario en la respuesta
        String token = jwtUtil.create(user);
        responseLoginDTO.setToken(token);
        responseLoginDTO.setUser(Optional.of(user));

        try {
            // Llamada para obtener los datos externos
            List<Map<String, Object>> companyList = fetchAndParseCompanyData(user, fuctions);
            responseLoginDTO.setListCompany(companyList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        responseLoginDTO.setMessage("Logeo Correcto");
        return responseLoginDTO;
    }

    /**
     * Obtiene y procesa los datos de las empresas y empleados asociados a un usuario.
     */
    private List<Map<String, Object>> fetchAndParseCompanyData(User user, Fuctions fuctions) throws IOException {
        String responseGet = fetchExternalCompanyData(user);
        List<Map<String, Object>> companyList = null;

        if (responseGet != null && !responseGet.isEmpty() && !responseGet.equals("[]")) {
            companyList = fuctions.parseData(responseGet);
            companyList = fuctions.extractCompanyIds(companyList);

            String employeeResponse = fetchExternalEmployeeData(user);
            List<Map<String, Object>> employeeList = fuctions.parseData(employeeResponse);

            // Unificar datos de empresas y empleados
            return fuctions.unifyCompanyAndEmployeeData(companyList, employeeList);
        } else {
            // Si no hay datos de empresas, obtener los datos directamente de los empleados
            responseGet = fetchExternalEmployeeData(user);
            return fuctions.parseData(responseGet);
        }
    }

    // Método para realizar la llamada a la API externa
    private String fetchExternalCompanyData(User user) throws IOException {
        String token = jwtUtil.create(user);
        String route = Apis.COMPANY + user.getUserId();
        return GetApi("GET", route, token, null);
    }

    // Método para realizar la llamada a la API externa
    private String fetchExternalEmployeeData(User user) throws IOException {
        String token = jwtUtil.create(user);
        String route = Apis.EMPLOYEE + user.getPerson().getPersonId();
        return GetApi("GET", route, token, null);
    }


}
