package com.sanyam.curewell.curewell_backend.service;

import com.sanyam.curewell.curewell_backend.entity.Role;
import com.sanyam.curewell.curewell_backend.payload.LoginDTO;
import com.sanyam.curewell.curewell_backend.payload.RegisterDTO;

import java.util.List;

public interface AuthService {
    String register(RegisterDTO registerDTO);
    String login(LoginDTO loginDTO);
    String createRole(Role role);
    String deleteRole(String name);
    List<Role> getAllRoles();
    String getRoleByName(String name);
}