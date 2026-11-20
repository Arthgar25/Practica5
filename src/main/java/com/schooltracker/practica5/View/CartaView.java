package com.schooltracker.practica5.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class CartaView extends VBox {
    private final CardCarouselView carouselView;
    private final CardDetailsTextView detailsTextView;

    public CartaView() {
        setSpacing(12);
        setPadding(new Insets(16));

        carouselView = new CardCarouselView();
        VBox.setVgrow(carouselView, Priority.ALWAYS);

        detailsTextView = new CardDetailsTextView();

        getChildren().addAll(carouselView, detailsTextView);
    }

    public void setOnPreviousClicked(Runnable handler) {
        carouselView.setOnPreviousClicked(handler);
    }

    public void setOnNextClicked(Runnable handler) {
        carouselView.setOnNextClicked(handler);
    }

    public void showInitialCard(Image previousMini, Image currentFull, Image nextMini,
                                String cardName, String rarity, String attributeType,
                                String levelRank, String raceSubtype, String attack,
                                String defense, String description,
                                int currentPosition, int totalCards) {
        carouselView.displayCards(previousMini, currentFull, nextMini);
        detailsTextView.setCardDetails(
                cardName, rarity, attributeType, levelRank,
                raceSubtype, attack, defense, description
        );
        detailsTextView.setCardCounter(currentPosition, totalCards);
    }

    public void navigateToCard(Image previousMini, Image currentFull, Image nextMini,
                               CardCarouselView.SlideDirection direction,
                               String cardName, String rarity, String attributeType,
                               String levelRank, String raceSubtype, String attack,
                               String defense, String description,
                               int currentPosition, int totalCards) {
        carouselView.slideToCards(previousMini, currentFull, nextMini, direction);
        detailsTextView.setCardDetails(
                cardName, rarity, attributeType, levelRank,
                raceSubtype, attack, defense, description
        );
        detailsTextView.setCardCounter(currentPosition, totalCards);
    }


}