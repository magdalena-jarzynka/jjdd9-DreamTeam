package com.infoshareacademy.dreamteam.cdi;

public enum Role {
    GUEST(1),
    USER(2),
    ADMIN(3),
    SUPER_ADMIN(4);

    private final int level;

    Role(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
