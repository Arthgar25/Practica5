package com.schooltracker.practica5.View;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.layout.HBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class CardCarouselView extends HBox {
    public enum SlideDirection{
        LEFT,
        RIGHT
    }
    private StackPane leftSlot;
    private StackPane centerSlot;
    private StackPane rightSlot;
    private ImageView leftImageView;
    private ImageView centerImageView;
    private ImageView rightImageView;

    private Runnable onPreviousClicked;
    private Runnable onNextClicked;
    private boolean isAnimating = false;

    private static final double CENTER_WIDTH = 190;
    private static final double CENTER_HEIGHT = 275;
    private static final double SIDE_WIDTH = 120;
    private static final double SIDE_HEIGHT = 175;
    private static final double SIDE_SCALE = 0.78;
    private static final double SIDE_OPACITY = 0.65;


    public CardCarouselView() {
        setAlignment(Pos.CENTER);
        setSpacing(24);
        getStyleClass().add("card-carousel-view");

        leftImageView = createImageView(SIDE_WIDTH, SIDE_HEIGHT, SIDE_SCALE, SIDE_OPACITY);
        centerImageView = createImageView(CENTER_WIDTH, CENTER_HEIGHT, 1.0, 1.0);
        rightImageView = createImageView(SIDE_WIDTH, SIDE_HEIGHT, SIDE_SCALE, SIDE_OPACITY);

        leftSlot = new StackPane(leftImageView);
        centerSlot = new StackPane(centerImageView);
        rightSlot = new StackPane(rightImageView);

        // Left and right thumbnails are clickable; center active card is non-clickable
        leftSlot.setCursor(Cursor.HAND);
        rightSlot.setCursor(Cursor.HAND);
        centerSlot.setCursor(Cursor.DEFAULT);

        leftSlot.setOnMouseClicked(e -> {
            if (!isAnimating && onPreviousClicked != null) {
                onPreviousClicked.run();
            }
        });

        rightSlot.setOnMouseClicked(e -> {
            if (!isAnimating && onNextClicked != null) {
                onNextClicked.run();
            }
        });

        getChildren().addAll(leftSlot, centerSlot, rightSlot);
        resetViewOrder();
    }

    private ImageView createImageView(double width, double height, double scale, double opacity) {
        ImageView view = new ImageView();
        view.setFitWidth(width);
        view.setFitHeight(height);
        view.setPreserveRatio(true);
        view.setSmooth(true);
        view.setScaleX(scale);
        view.setScaleY(scale);
        view.setOpacity(opacity);
        return view;
    }

    public void setOnPreviousClicked(Runnable onPreviousClicked) {
        this.onPreviousClicked = onPreviousClicked;
    }

    private void resetViewOrder() {
        leftSlot.setViewOrder(0);
        centerSlot.setViewOrder(-1);
        rightSlot.setViewOrder(0);
    }

}
