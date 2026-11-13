package com.schooltracker.practica5.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CartaView extends VBox {
    private TextField nameField;
    private TextField rarityField;
    private TextField attributeTypeField;
    private TextField levelRankField;
    private TextField raceSubtypeField;
    private TextField attackField;
    private TextField defenseField;
    private TextArea descriptionArea;

    public CartaView() {
        setSpacing(15);
        setPadding(new Insets(20));
        getStyleClass().add("card-details-view");

        Label titleLabel = new Label("Card Information");
        titleLabel.getStyleClass().add("view-title");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(12);

        nameField = createStyledTextField();
        rarityField = createStyledTextField();
        attributeTypeField = createStyledTextField();
        levelRankField = createStyledTextField();
        raceSubtypeField = createStyledTextField();
        attackField = createStyledTextField();
        defenseField = createStyledTextField();

        descriptionArea = new TextArea();
        descriptionArea.setWrapText(true);
        descriptionArea.setPrefRowCount(4);
        descriptionArea.getStyleClass().add("card-description-area");

        int row = 0;
        gridPane.add(new Label("Card Name:"), 0, row);
        gridPane.add(nameField, 1, row++);

        gridPane.add(new Label("Rarity:"), 0, row);
        gridPane.add(rarityField, 1, row++);

        gridPane.add(new Label("Attribute/Card Type:"), 0, row);
        gridPane.add(attributeTypeField, 1, row++);

        gridPane.add(new Label("Level/Rank:"), 0, row);
        gridPane.add(levelRankField, 1, row++);

        gridPane.add(new Label("Race/Subtype:"), 0, row);
        gridPane.add(raceSubtypeField, 1, row++);

        HBox statsBox = new HBox(10);
        statsBox.setAlignment(Pos.CENTER_LEFT);
        attackField.setPrefWidth(90);
        defenseField.setPrefWidth(90);
        statsBox.getChildren().addAll(new Label("ATK:"), attackField, new Label("DEF:"), defenseField);

        gridPane.add(new Label("Stats:"), 0, row);
        gridPane.add(statsBox, 1, row++);

        VBox descBox = new VBox(5);
        Label descLabel = new Label("Card Description:");
        descBox.getChildren().addAll(descLabel, descriptionArea);

        getChildren().addAll(titleLabel, gridPane, descBox);
    }

    private TextField createStyledTextField() {
        TextField field = new TextField();
        field.getStyleClass().add("card-text-field");
        return field;
    }

    public void setCardData(String name, String rarity, String attributeType, String levelRank, String raceSubtype, String atk, String def, String description) {
        nameField.setText(name);
        rarityField.setText(rarity);
        attributeTypeField.setText(attributeType);
        levelRankField.setText(levelRank);
        raceSubtypeField.setText(raceSubtype);
        attackField.setText(atk);
        defenseField.setText(def);
        descriptionArea.setText(description);
    }

    public String getCardName() {
        return nameField.getText();
    }

    public String getRarity() {
        return rarityField.getText();
    }

    public String getAttributeType() {
        return attributeTypeField.getText();
    }

    public String getLevelRank() {
        return levelRankField.getText();
    }

    public String getRaceSubtype() {
        return raceSubtypeField.getText();
    }

    public String getAttack() {
        return attackField.getText();
    }

    public String getDefense() {
        return defenseField.getText();
    }

    public String getDescription() {
        return descriptionArea.getText();
    }
}