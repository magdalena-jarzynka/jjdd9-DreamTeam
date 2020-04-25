package com.infoshareacademy;

public enum ConsoleColors {
    RESET("\033[0m"),
    RED  ("\033[0;31m"),
    BLUE ("\033[0;34m"),
    BLACK_BOLD ("\033[1;30m"),
    BLACK_UNDERLINED ("\033[4;30m");

    String colorType;
    ConsoleColors(String colorType) {
        this.colorType = colorType;
    }

    public String getColorType(){
        return colorType;

    }
}