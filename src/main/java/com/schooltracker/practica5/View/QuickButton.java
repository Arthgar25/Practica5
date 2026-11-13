package com.schooltracker.practica5.View;


import javafx.scene.control.Button;

public abstract class QuickButton extends Button {
    public QuickButton(String icon) {
        super(icon);
        this.setStyle("-fx-background-color: #333333; -fx-text-fill: #ffffff; -fx-cursor: hand; -fx-background-radius: 4px;");
    }
    protected abstract void addAction();
}