package com.schooltracker.practica5.View;

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

}
