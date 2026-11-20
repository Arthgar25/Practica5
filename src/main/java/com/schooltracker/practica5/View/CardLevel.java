package com.schooltracker.practica5.View;

public enum CardLevel {
    LEVEL_1(1),
    LEVEL_2(2),
    LEVEL_3(3),
    LEVEL_4(4),
    LEVEL_5(5),
    LEVEL_6(6),
    LEVEL_7(7),
    LEVEL_8(8),
    LEVEL_9(9),
    LEVEL_10(10),
    LEVEL_11(11),
    LEVEL_12(12);

    private final int levelNumber;
    private final String label;

    CardLevel(int levelNumber) {
        this.levelNumber = levelNumber;
        this.label = "Level " + levelNumber;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return label;
    }
}