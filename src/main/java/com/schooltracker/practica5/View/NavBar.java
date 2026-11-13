package com.schooltracker.practica5.View;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class NavBar extends HBox {

    private final Label titleLabel;
    private final TextField searchField;
    private final HBox actionsBox;
    private final QuickButton themeToggleButton;
    private final QuickButton cardView;
    private final QuickButton listView;

    private boolean isDarkMode = true;

    public NavBar(String appTitle) {
        this.setAlignment(Pos.CENTER_LEFT);
        this.setSpacing(15);
        this.setStyle("-fx-background-color: #252526; -fx-padding: 10px 20px; -fx-border-color: #333333; -fx-border-width: 0 0 1 0;");

        this.titleLabel = new Label(appTitle);
        this.titleLabel.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 16px; -fx-font-weight: bold;");

        this.searchField = new TextField();
        this.searchField.setPromptText("Search...");
        this.searchField.setStyle("-fx-background-color: #3c3c3c; -fx-text-fill: #ffffff; -fx-prompt-text-fill: #888888; -fx-background-radius: 4px;");
        this.searchField.setPrefWidth(220);

        this.cardView = new QuickButton("Card View") {
            @Override
            protected void addAction() {}
        };

        this.listView = new QuickButton("List View") {
            @Override
            protected void addAction() {}
        };

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        this.actionsBox = new HBox(10);
        this.actionsBox.setAlignment(Pos.CENTER);

        this.themeToggleButton = new QuickButton("🌙") {
            @Override
            protected void addAction() {
                setOnAction(event -> toggleTheme());
            }
        };

        this.getChildren().addAll(
                titleLabel,
                searchField,
                cardView,
                listView,
                spacer,
                actionsBox,
                themeToggleButton
        );
    }

    public void setTitle(String title) {
        this.titleLabel.setText(title);
    }

    public void addAction(Button button) {
        button.setStyle("-fx-background-color: #007acc; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand; -fx-background-radius: 4px;");
        this.actionsBox.getChildren().add(button);
    }

    public void onSearch(java.util.function.Consumer<String> searchConsumer) {
        this.searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            searchConsumer.accept(newValue);
        });
    }

    private void toggleTheme() {
        isDarkMode = !isDarkMode;
        if (isDarkMode) {
            this.themeToggleButton.setText("🌙");
            this.setStyle("-fx-background-color: #252526; -fx-padding: 10px 20px; -fx-border-color: #333333; -fx-border-width: 0 0 1 0;");
            this.titleLabel.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 16px; -fx-font-weight: bold;");
        } else {
            this.themeToggleButton.setText("☀️");
            this.setStyle("-fx-background-color: #f3f3f3; -fx-padding: 10px 20px; -fx-border-color: #cccccc; -fx-border-width: 0 0 1 0;");
            this.titleLabel.setStyle("-fx-text-fill: #1e1e1e; -fx-font-size: 16px; -fx-font-weight: bold;");
        }
    }
}