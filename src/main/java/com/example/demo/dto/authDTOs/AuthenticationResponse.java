package com.example.demo.dto.authDTOs;

import lombok.Getter;

import java.util.Set;

@Getter
public class AuthenticationResponse {

    private String token;
    private String type = "Bearer";
    private String username;
    private Set<String> roles;

    public AuthenticationResponse(String token, String username, Set<String> roles) {
        this.token = token;
        this.username = username;
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
