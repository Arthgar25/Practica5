package com.schooltracker.practica5.View;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CartaListView extends HBox{
    private ImageView imageView;
    private VBox datos;
    private Label cardName;
    private HBox atributos;
    private Label attributeCardType;
    private Label level;
    private Label raceSubtype;
    private Label atk;
    private Label def;
    private TextArea description;

    public CartaListView() {
        super();
        imageView = new ImageView();
        datos = new VBox();
        cardName = new Label();
        atributos = new HBox();
        attributeCardType = new Label();
        level = new Label();
        raceSubtype = new Label();
        atk = new Label();
        def = new Label();
        description = new TextArea();

        this.atributos.getChildren().addAll(attributeCardType, level, raceSubtype, atk, def);

        this.datos.getChildren().addAll(cardName, atributos, description);

        this.getChildren().addAll(imageView, datos);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public Label getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName.setText(cardName);
    }

    public String getAttributeCardType() {
        return attributeCardType.getText();
    }

    public void setAttributeCardType(String attributeCardType) {
        this.attributeCardType.setText(attributeCardType);
    }

    public String getLevel() {
        return level.getText();
    }

    public void setLevel(String level) {
        this.level.setText(level);
    }

    public String getRaceSubtype() {
        return raceSubtype.getText();
    }

    public void setRaceSubtype(String raceSubtype) {
        this.raceSubtype.setText(raceSubtype);
    }

    public String getAtk() {
        return atk.getText();
    }

    public void setAtk(String atk) {
        this.atk.setText(atk);
    }

    public String getDef() {
        return def.getText();
    }

    public void setDef(String def) {
        this.def.setText(def);
    }

    public String getDescription() {
        return description.getText();
    }

    public void setDescription(String description) {
        this.description.setText(description);
    }
}
