package com.se.ue04.model;

import com.se.ue04.Constants;

import javax.persistence.*;

@Entity
public class User {

    @Id
    private String email;
    private String name;
    private String password;
    private String role;

    public User() {
        this.role = Constants.STANDARD_USER;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
