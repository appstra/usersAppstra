package com.appstra.users.controller;

import com.appstra.users.entity.User;
import com.appstra.users.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/saveuser")
    @Operation(summary = "Guardar Usuario", description = "Guardar usuario")
    public ResponseEntity<User> saveUser (@Validated @RequestBody User user){
        return ResponseEntity.ok(userService.saveUser(user));
    }
    @PutMapping("/updateuser")
    @Operation(summary = "actualiza Usuario", description = "actualiza Usuario")
    public ResponseEntity<User> updateUser (@Validated @RequestBody User user){
        return ResponseEntity.ok(userService.upDateUser(user));
    }
    @DeleteMapping("/deleteuser/{userId}")
    @Operation(summary = "Elimina Usuario", description = "Elimina Usuario")
    public ResponseEntity<Boolean> deleteUser (@PathVariable("userId") Integer userId){
        return ResponseEntity.ok(userService.deleteUser(userId));
    }
    @GetMapping("/listuser")
    @Operation(summary = "Lista Usuarios", description = "Lista Usuarios")
    public ResponseEntity<List<User>> listState (){
        return ResponseEntity.ok(userService.listUser());
    }
}
