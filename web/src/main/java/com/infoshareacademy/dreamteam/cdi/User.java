package com.infoshareacademy.dreamteam.cdi;

import javax.enterprise.inject.Model;

@Model
public class User {
    private Role role = Role.ADMIN;
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

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

}
