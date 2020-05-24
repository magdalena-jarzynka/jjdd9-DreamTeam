package com.infoshareacademy.dreamteam.cdi;

import javax.enterprise.inject.Model;

@Model
public class User {
    private Long id;
    private String name;
    private String email;
    private Role role = Role.GUEST;
    private boolean isLoggedIn;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isUserOrHigher() {
        return this.role.getLevel() >= Role.USER.getLevel();
    }

    public boolean isAdminOrHigher() {
        return this.role.getLevel() >= Role.ADMIN.getLevel();
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
