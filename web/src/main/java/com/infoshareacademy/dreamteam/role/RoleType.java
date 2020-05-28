package com.infoshareacademy.dreamteam.role;

public enum RoleType {
    GUEST(1),
    USER(2),
    ADMIN(3),
    SUPER_ADMIN(4);

    private final int level;

    RoleType(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

}
