package com.schooltracker.practica5.View;

import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class SideBar extends VBox {
    private final QuickButton showAllCards;
    private final QuickButton showByLevel;
    private final ComboBox<String> levelComboBox;
    private final QuickButton showByAttribute;
    private final ComboBox<String> attributeComboBox;
    private final QuickButton showByAttackPoints;
    private final CheckBox attackSortCheckBox;
    private final QuickButton showByDefensePoints;
    private final CheckBox defenseSortCheckBox;

    public SideBar() {
        double sidebarWidth = 220.0;
        this.setPrefWidth(Double.valueOf(sidebarWidth));
        this.setAlignment(Pos.TOP_LEFT);
        this.setSpacing(15);
        this.setStyle("-fx-background-color: #252526;" +
                " -fx-padding: 15px 20px;" +
                " -fx-border-color: #333333;" +
                " -fx-border-width: 0 1 0 0;");

        this.showAllCards = new QuickButton("Show all cards") {
            @Override
            protected void addAction() {

            }
        };

        this.showByLevel = new QuickButton("Show by level") {
            @Override
            protected void addAction() {

            }
        };
        this.levelComboBox = new ComboBox<>();
        this.levelComboBox.setPromptText("Choose level");
        this.levelComboBox.setMaxWidth(Double.MAX_VALUE);

        this.showByAttribute = new QuickButton("Show by attribute") {
            @Override
            protected void addAction() {

            }
        };
        this.attributeComboBox = new ComboBox<>();
        this.attributeComboBox.setPromptText("Choose attribute");
        this.attributeComboBox.setMaxWidth(Double.MAX_VALUE);

        this.showByAttackPoints = new QuickButton("Show by attack points") {
            @Override
            protected void addAction() {

            }
        };
        this.attackSortCheckBox = new CheckBox("Low to high");
        this.attackSortCheckBox.setStyle("-fx-text-fill: white;");

        this.showByDefensePoints = new QuickButton("Show by defense points") {
            @Override
            protected void addAction() {

            }
        };
        this.defenseSortCheckBox = new CheckBox("Low to high");
        this.defenseSortCheckBox.setStyle("-fx-text-fill: white;");

        VBox levelBox = new VBox(5, showByLevel, levelComboBox);
        VBox attributeBox = new VBox(5, showByAttribute, attributeComboBox);
        VBox attackBox = new VBox(5, showByAttackPoints, attackSortCheckBox);
        VBox defenseBox = new VBox(5, showByDefensePoints, defenseSortCheckBox);

        this.getChildren().addAll(
                showAllCards,
                levelBox,
                attributeBox,
                attackBox,
                defenseBox
        );
    }
}