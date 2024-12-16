package com.appstra.users.controller;

import com.appstra.users.config.JwtUtil;
import com.appstra.users.dto.LoginDTO;
import com.appstra.users.dto.ResponseLoginDTO;
import com.appstra.users.entity.User;
import com.appstra.users.repository.UserRepository;
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

import java.util.Optional;

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
    public ResponseEntity<ResponseLoginDTO> login(@RequestBody LoginDTO loginDTO){
        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
        Authentication authentication = this.authenticationManager.authenticate(login);
        ResponseLoginDTO responseLoginDTO = new ResponseLoginDTO();
        if(authentication.isAuthenticated()){
            Optional<User> user = userRepository.findByUserUser(loginDTO.getUsername());
            User userObjet = user.orElseThrow(() -> new UsernameNotFoundException("User not found"));
            responseLoginDTO.setToken(this.jwtUtil.create(userObjet)); // crea el tocket
            responseLoginDTO.setUser(user);
            responseLoginDTO.setMessage("Logeo Correcto");
        }
        //System.out.println("auntentexacion fue: " + authentication.isAuthenticated());
        //System.out.println("Informacion de la autenticacion: " +authentication.getPrincipal());

        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION,responseLoginDTO.getToken()).body(responseLoginDTO);
    }

}
