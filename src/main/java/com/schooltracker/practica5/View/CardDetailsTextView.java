package com.schooltracker.practica5.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class CardDetailsTextView extends VBox {
    private Label titleLabel;
    private HBox metaStatsRow;
    private Label attributeLabel;
    private Label sep1Label;
    private Label levelRankLabel;
    private Label sep2Label;
    private Label raceSubtypeLabel;
    private Region spacer;
    private Label atkLabel;
    private Label sep3Label;
    private Label defLabel;
    private Label sep4Label;
    private Label counterLabel;
    private Label descriptionLabel;
    private HBox bottomRow;
    private Region bottomSpacer;
    private Label rarityBadgeLabel;

    public CardDetailsTextView() {
        setSpacing(10);
        setPadding(new Insets(16, 20, 16, 20));

        titleLabel = new Label();
        attributeLabel = new Label();
        sep1Label = createSeparator();
        levelRankLabel = new Label();
        sep2Label = createSeparator();
        raceSubtypeLabel = new Label();
        spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        atkLabel = new Label();
        sep3Label = createSeparator();
        defLabel = new Label();
        sep4Label = createSeparator();
        counterLabel = new Label();
        metaStatsRow = new HBox(8);
        metaStatsRow.setAlignment(Pos.CENTER_LEFT);
        metaStatsRow.getChildren().addAll(
                attributeLabel, sep1Label,
                levelRankLabel, sep2Label,
                raceSubtypeLabel, spacer,
                atkLabel, sep3Label,
                defLabel, sep4Label,
                counterLabel
        );

        descriptionLabel = new Label();
        descriptionLabel.setWrapText(true);

        bottomSpacer = new Region();
        HBox.setHgrow(bottomSpacer, Priority.ALWAYS);

        rarityBadgeLabel = new Label();

        bottomRow = new HBox();
        bottomRow.setAlignment(Pos.CENTER_RIGHT);
        bottomRow.getChildren().addAll(bottomSpacer, rarityBadgeLabel);

        getChildren().addAll(titleLabel, metaStatsRow, descriptionLabel, bottomRow);
    }

    private Label createSeparator() {
        Label sep = new Label("|");
        return sep;
    }
}
