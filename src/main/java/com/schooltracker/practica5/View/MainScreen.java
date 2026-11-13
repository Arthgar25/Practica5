package com.schooltracker.practica5.View;

import javafx.application.Application;
import javafx.geometry.Side;
import javafx.scene.layout.BorderPane;

public class MainScreen extends BorderPane {
    NavBar navBar = new NavBar("Yu-Gi-Oh! DB");
    SideBar sideBar = new SideBar();
    public MainScreen() {
        super(new BorderPane());
        this.setTop(navBar);
        this.setLeft(sideBar);

    }
}
