package com.schooltracker.practica5.Model;

public class Carta {
    private String cardNumber;
    private String cardName;
    private String rarity;
    private String attributeType;
    private String levelRank;
    private String raceSubtype;
    private String attack;
    private String defense;
    private String description;

    public Carta(String cardNumber, String cardName, String rarity, String attributeType, String levelRank, String raceSubtype, String attack, String defense, String description) {
        this.cardNumber = cardNumber;
        this.cardName = cardName;
        this.rarity = rarity;
        this.attributeType = attributeType;
        this.levelRank = levelRank;
        this.raceSubtype = raceSubtype;
        this.attack = attack;
        this.defense = defense;
        this.description = description;
    }

    public String getImageFileName(){
        String prefix = (cardNumber != null && !cardNumber.isBlank())
                ? cardNumber.trim()
                : "";
        String formattedName = (cardName != null ? cardName.trim() : "")
                .replaceAll("\\s+", "_");

        if (!prefix.isEmpty()) {
            return prefix + "_" + formattedName + ".jpg";
        }
        return formattedName + ".jpg";
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getAttributeType() {
        return attributeType;
    }

    public void setAttributeType(String attributeType) {
        this.attributeType = attributeType;
    }

    public String getLevelRank() {
        return levelRank;
    }

    public void setLevelRank(String levelRank) {
        this.levelRank = levelRank;
    }

    public String getRaceSubtype() {
        return raceSubtype;
    }

    public void setRaceSubtype(String raceSubtype) {
        this.raceSubtype = raceSubtype;
    }

    public String getAttack() {
        return attack;
    }

    public void setAttack(String attack) {
        this.attack = attack;
    }

    public String getDefense() {
        return defense;
    }

    public void setDefense(String defense) {
        this.defense = defense;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}