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
    private final QuickButton weekView;
    private final QuickButton calendarView;
    private final QuickButton dayView;

    private boolean isDarkMode = true;

    public NavBar(String appTitle) {
        // --- 1. Basic HBox Setup ---
        this.setAlignment(Pos.CENTER_LEFT);
        this.setSpacing(15);
        this.setStyle("-fx-background-color: #252526; -fx-padding: 10px 20px; -fx-border-color: #333333; -fx-border-width: 0 0 1 0;");

        // --- 2. Title Section ---
        this.titleLabel = new Label(appTitle);
        this.titleLabel.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 16px; -fx-font-weight: bold;");

        // --- 3. Functions ---
        this.searchField = new TextField();
        this.searchField.setPromptText("Search...");
        this.searchField.setStyle("-fx-background-color: #3c3c3c; -fx-text-fill: #ffffff; -fx-prompt-text-fill: #888888; -fx-background-radius: 4px;");
        this.searchField.setPrefWidth(220);

        this.weekView = new QuickButton("Week") {
            @Override
            protected void addAction() {}
        };

        this.dayView = new QuickButton("Day") {
            @Override
            protected void addAction() {}
        };

        this.calendarView = new QuickButton("Calendar") {
            @Override
            protected void addAction() {}
        };

        // --- 4. Spacer ---
        // Pushes everything after it to the far right
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // --- 5. Custom Actions Zone ---
        this.actionsBox = new HBox(10);
        this.actionsBox.setAlignment(Pos.CENTER);

        // --- 6. Theme Toggle Button ---
        this.themeToggleButton = new QuickButton("🌙") {
            @Override
            protected void addAction() {
                setOnAction(event -> toggleTheme());
            }
        };

        // --- 7. Assemble Children ---
        this.getChildren().addAll(
                titleLabel,
                dayView,
                weekView,
                calendarView,
                spacer,
                actionsBox,
                themeToggleButton
        );
    }

    // ==========================================
    // Framework Helper API Methods
    // ==========================================

    /**
     * Set or change the title displayed in the NavBar.
     */
    public void setTitle(String title) {
        this.titleLabel.setText(title);
    }

    /**
     * Add a custom action button to the right side of the navbar.
     */
    public void addAction(Button button) {
        button.setStyle("-fx-background-color: #007acc; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand; -fx-background-radius: 4px;");
        this.actionsBox.getChildren().add(button);
    }

    /**
     * Set a listener when the search field text changes.
     */
    public void onSearch(java.util.function.Consumer<String> searchConsumer) {
        this.searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            searchConsumer.accept(newValue);
        });
    }

    /**
     * Simple theme toggle handler.
     */
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