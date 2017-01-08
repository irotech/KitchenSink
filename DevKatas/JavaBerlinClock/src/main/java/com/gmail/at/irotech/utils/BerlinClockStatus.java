package com.gmail.at.irotech.utils;

/**
 * Created by irotech on 13/12/2016.
 */
public enum BerlinClockStatus {
        OFF("O"),
        RED("R"),
        YELLOW("Y");

    private String color;

    BerlinClockStatus(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

}
