package com.schooltracker.practica5.View;

import javafx.scene.layout.BorderPane;

public class MainScreen extends BorderPane {
    private final NavBar navBar = new NavBar("Yu-Gi-Oh! DB");
    private final SideBar sideBar = new SideBar();

    public MainScreen() {
        super();
        this.setTop(navBar);
        this.setLeft(sideBar);
    }

    public NavBar getNavBar() {
        return navBar;
    }

    public SideBar getSideBar() {
        return sideBar;
    }
}