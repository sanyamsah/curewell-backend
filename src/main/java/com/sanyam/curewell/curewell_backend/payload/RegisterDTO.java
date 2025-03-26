package com.sanyam.curewell.curewell_backend.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {
    @NotEmpty(message = "Name is required")
    private String name;

    @NotEmpty(message = "Email is required")
    @Email(message = "Email is not valid")
    private String email;

    @NotEmpty(message = "Username is required")
    private String username;

    @NotEmpty(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    @NotEmpty(message = "Role is required")
    private String role;

    public @NotEmpty(message = "Name is required") String getName() {
        return name;
    }

    public void setName(@NotEmpty(message = "Name is required") String name) {
        this.name = name;
    }

    public @NotEmpty(message = "Email is required") @Email(message = "Email is not valid") String getEmail() {
        return email;
    }

    public void setEmail(@NotEmpty(message = "Email is required") @Email(message = "Email is not valid") String email) {
        this.email = email;
    }

    public @NotEmpty(message = "Username is required") String getUsername() {
        return username;
    }

    public void setUsername(@NotEmpty(message = "Username is required") String username) {
        this.username = username;
    }

    public @NotEmpty(message = "Password is required") @Size(min = 6, message = "Password must be at least 6 characters long") String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty(message = "Password is required") @Size(min = 6, message = "Password must be at least 6 characters long") String password) {
        this.password = password;
    }

    public @NotEmpty(message = "Role is required") String getRole() {
        return role;
    }

    public void setRole(@NotEmpty(message = "Role is required") String role) {
        this.role = role;
    }
}
