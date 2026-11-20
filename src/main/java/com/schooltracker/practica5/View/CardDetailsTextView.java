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
        metaStatsRow.getChildren().addAll(attributeLabel, sep1Label, levelRankLabel, sep2Label, raceSubtypeLabel, spacer, atkLabel, sep3Label, defLabel, sep4Label, counterLabel);

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

    public void setCardDetails(String cardName, String rarity, String attributeType, String levelRank, String raceSubtype, String attack, String defense, String description) {
        titleLabel.setText(safeText(cardName));

        String attr = safeText(attributeType);
        attributeLabel.setText(attr);
        attributeLabel.setVisible(!attr.isEmpty());
        attributeLabel.setManaged(!attr.isEmpty());

        String lvl = formatLevel(levelRank);
        levelRankLabel.setText(lvl);
        levelRankLabel.setVisible(!lvl.isEmpty());
        levelRankLabel.setManaged(!lvl.isEmpty());

        String race = formatRaceSubtype(raceSubtype);
        raceSubtypeLabel.setText(race);
        raceSubtypeLabel.setVisible(!race.isEmpty());
        raceSubtypeLabel.setManaged(!race.isEmpty());

        String atk = formatStat("ATK", attack);
        atkLabel.setText(atk);
        atkLabel.setVisible(!atk.isEmpty());
        atkLabel.setManaged(!atk.isEmpty());

        String def = formatStat("DEF", defense);
        defLabel.setText(def);
        defLabel.setVisible(!def.isEmpty());
        defLabel.setManaged(!def.isEmpty());

        updateSeparators();

        descriptionLabel.setText(safeText(description));

        String rarityCode = extractShortRarity(rarity);
        rarityBadgeLabel.setText(rarityCode);
        rarityBadgeLabel.setVisible(!rarityCode.isEmpty());
        rarityBadgeLabel.setManaged(!rarityCode.isEmpty());
    }

    public void setCardCounter(int currentPosition, int totalCards) {
        if (totalCards <= 0 || currentPosition <= 0) {
            counterLabel.setText("");
            counterLabel.setVisible(false);
            counterLabel.setManaged(false);
        } else {
            counterLabel.setText("[" + currentPosition + "/" + totalCards + "]");
            counterLabel.setVisible(true);
            counterLabel.setManaged(true);
        }
        updateSeparators();
    }

    private void updateSeparators() {
        boolean hasAttr = attributeLabel.isManaged();
        boolean hasLvl = levelRankLabel.isManaged();
        boolean hasRace = raceSubtypeLabel.isManaged();
        boolean hasAtk = atkLabel.isManaged();
        boolean hasDef = defLabel.isManaged();
        boolean hasCounter = counterLabel.isManaged();

        sep1Label.setVisible(hasAttr && (hasLvl || hasRace));
        sep1Label.setManaged(sep1Label.isVisible());

        sep2Label.setVisible(hasLvl && hasRace);
        sep2Label.setManaged(sep2Label.isVisible());

        sep3Label.setVisible(hasAtk && hasDef);
        sep3Label.setManaged(sep3Label.isVisible());

        sep4Label.setVisible((hasAtk || hasDef) && hasCounter);
        sep4Label.setManaged(sep4Label.isVisible());
    }

    private String formatLevel(String levelRank) {
        String clean = safeText(levelRank);
        if (clean.isEmpty() || clean.equals("-") || clean.equalsIgnoreCase("N/A")) {
            return "";
        }
        if (clean.toLowerCase().startsWith("level") || clean.toLowerCase().startsWith("rank")) {
            return clean;
        }
        return "Level " + clean;
    }

    private String formatRaceSubtype(String raceSubtype) {
        String clean = safeText(raceSubtype);
        if (clean.isEmpty() || clean.equals("-") || clean.equalsIgnoreCase("N/A")) {
            return "";
        }
        if (clean.startsWith("[") && clean.endsWith("]")) {
            return clean;
        }
        return "[ " + clean + " ]";
    }

    private String formatStat(String prefix, String value) {
        String clean = safeText(value);
        if (clean.isEmpty() || clean.equals("-") || clean.equalsIgnoreCase("N/A")) {
            return "";
        }
        if (clean.toUpperCase().startsWith(prefix)) {
            return clean;
        }
        return prefix + " " + clean;
    }

    private String extractShortRarity(String rarity) {
        String clean = safeText(rarity);
        if (clean.isEmpty()) {
            return "";
        }
        int spaceIndex = clean.indexOf(' ');
        return spaceIndex > 0 ? clean.substring(0, spaceIndex) : clean;
    }

    private String safeText(String value) {
        return value == null ? "" : value.trim();
    }
}
