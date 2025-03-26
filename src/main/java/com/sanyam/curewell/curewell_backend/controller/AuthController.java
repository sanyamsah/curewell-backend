package com.sanyam.curewell.curewell_backend.controller;

import com.sanyam.curewell.curewell_backend.entity.Role;
import com.sanyam.curewell.curewell_backend.payload.LoginDTO;
import com.sanyam.curewell.curewell_backend.payload.RegisterDTO;
import com.sanyam.curewell.curewell_backend.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody @Valid RegisterDTO registerDTO) {
        String message = authService.register(registerDTO);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody @Valid LoginDTO loginDTO) {
        String message = authService.login(loginDTO);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("/role")
    public ResponseEntity<String> createRole(@RequestBody Role role) {
        var data = authService.createRole(role);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        var data = authService.getAllRoles();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/role/{name}")
    public ResponseEntity<String> getRoleByName(@PathVariable("name") String name) {
        var data = authService.getRoleByName(name);
        return ResponseEntity.ok(data);
    }

    @DeleteMapping("/role/{name}")
    public ResponseEntity<String> deleteRole(@PathVariable("name") String name) {
        var data = authService.deleteRole(name);
        return ResponseEntity.ok(data);
    }
}
