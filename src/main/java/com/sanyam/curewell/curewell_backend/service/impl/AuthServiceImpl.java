package com.sanyam.curewell.curewell_backend.service.impl;

import com.sanyam.curewell.curewell_backend.entity.Role;
import com.sanyam.curewell.curewell_backend.entity.User;
import com.sanyam.curewell.curewell_backend.exception.AuthException;
import com.sanyam.curewell.curewell_backend.payload.LoginDTO;
import com.sanyam.curewell.curewell_backend.payload.RegisterDTO;
import com.sanyam.curewell.curewell_backend.repository.RoleRepository;
import com.sanyam.curewell.curewell_backend.repository.UserRepository;
import com.sanyam.curewell.curewell_backend.security.JwtTokenProvider;
import com.sanyam.curewell.curewell_backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public String register(RegisterDTO registerDTO) {
        // check if email or username exists in database
        if(userRepository.existsByUsername(registerDTO.getUsername())){
            throw new AuthException(HttpStatus.BAD_REQUEST, "Username is already taken!");
        }
        if(userRepository.existsByEmail(registerDTO.getEmail())){
            throw new AuthException(HttpStatus.BAD_REQUEST, "Email is already taken!");
        }
        // create new user
        User user = new User();
        user.setName(registerDTO.getName());
        user.setEmail(registerDTO.getEmail());
        user.setUsername(registerDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        // assign role to user
        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findByName(registerDTO.getRole()).get();
        roles.add(role);
        user.setRoles(roles);

        // save user
        userRepository.save(user);
        return "User registered successfully!";
    }

    @Override
    public String login(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getUsernameOrEmail(), loginDTO.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);
        System.out.println(token);
        return token;
    }

    @Override
    public String createRole(Role role) {
        roleRepository.save(role);
        return "Role created successfully!";
    }
    @Override
    public String deleteRole(String name) {
        roleRepository.deleteByName(name);
        return "Role deleted successfully!";
    }
    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
    @Override
    public String getRoleByName(String name) {
        return roleRepository.findByName(name).get().getName();
    }
}