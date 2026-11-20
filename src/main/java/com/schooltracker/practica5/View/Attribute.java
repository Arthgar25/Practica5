package com.schooltracker.practica5.View;

public enum Attribute {
    DARK("DARK"),
    LIGHT("LIGHT"),
    WATER("WATER"),
    FIRE("FIRE"),
    EARTH("EARTH"),
    WIND("WIND"),
    DIVINE("DIVINE"),
    SPELL("SPELL"),
    TRAP("TRAP");

    private final String label;

    Attribute(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return label;
    }
}