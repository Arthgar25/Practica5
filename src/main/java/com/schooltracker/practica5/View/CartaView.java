package com.schooltracker.practica5.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
        getStyleClass().add("carta-view");

        carouselView = new CardCarouselView();
        VBox.setVgrow(carouselView, Priority.ALWAYS);

        detailsTextView = new CardDetailsTextView();

        getChildren().addAll(carouselView, detailsTextView);
    }

}