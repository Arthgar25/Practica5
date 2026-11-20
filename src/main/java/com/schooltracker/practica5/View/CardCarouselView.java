package com.schooltracker.practica5.View;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Bounds;

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
    private static final double PROMOTE_TO_CENTER_SCALE = CENTER_WIDTH / SIDE_WIDTH;
    private static final double DEMOTE_TO_SIDE_SCALE = SIDE_WIDTH / CENTER_WIDTH;
    private static final double FALLBACK_SLIDE_DISTANCE = 140;
    private static final Duration ANIM_DURATION = Duration.millis(400);


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

    public void setOnNextClicked(Runnable onNextClicked) {
        this.onNextClicked = onNextClicked;
    }

    public void displayCards(Image previousMini, Image currentFull, Image nextMini) {
        resetTransforms();
        leftImageView.setImage(previousMini);
        centerImageView.setImage(currentFull);
        rightImageView.setImage(nextMini);
    }

    public void slideToCards(Image previousMini, Image currentFull, Image nextMini, SlideDirection direction) {
        if (isAnimating) {
            displayCards(previousMini, currentFull, nextMini);
            return;
        }
        isAnimating = true;

        boolean movingToNext = direction == SlideDirection.RIGHT;
        StackPane incomingSlot = movingToNext ? rightSlot : leftSlot;
        ImageView incomingView = movingToNext ? rightImageView : leftImageView;
        StackPane outgoingTargetSlot = movingToNext ? leftSlot : rightSlot;
        ImageView outgoingTargetView = movingToNext ? leftImageView : rightImageView;

        incomingSlot.setViewOrder(-1);
        centerSlot.setViewOrder(0);
        outgoingTargetSlot.setViewOrder(1);

        double centerToSideDeltaX = computeDeltaX(centerSlot, outgoingTargetSlot, movingToNext ? -FALLBACK_SLIDE_DISTANCE : FALLBACK_SLIDE_DISTANCE);
        double incomingToCenterDeltaX = computeDeltaX(incomingSlot, centerSlot, movingToNext ? -FALLBACK_SLIDE_DISTANCE : FALLBACK_SLIDE_DISTANCE);
        double outerFadeDeltaX = movingToNext ? -FALLBACK_SLIDE_DISTANCE * 0.6 : FALLBACK_SLIDE_DISTANCE * 0.6;

        Interpolator smooth = Interpolator.EASE_BOTH;

        TranslateTransition centerToSideMove = new TranslateTransition(ANIM_DURATION, centerSlot);
        centerToSideMove.setToX(centerToSideDeltaX);
        centerToSideMove.setInterpolator(smooth);

        ScaleTransition centerToSideScale = new ScaleTransition(ANIM_DURATION, centerImageView);
        centerToSideScale.setToX(DEMOTE_TO_SIDE_SCALE);
        centerToSideScale.setToY(DEMOTE_TO_SIDE_SCALE);
        centerToSideScale.setInterpolator(smooth);

        FadeTransition centerToSideOpacity = new FadeTransition(ANIM_DURATION, centerImageView);
        centerToSideOpacity.setToValue(SIDE_OPACITY);
        centerToSideOpacity.setInterpolator(smooth);

        TranslateTransition incomingToCenterMove = new TranslateTransition(ANIM_DURATION, incomingSlot);
        incomingToCenterMove.setToX(incomingToCenterDeltaX);
        incomingToCenterMove.setInterpolator(smooth);

        ScaleTransition incomingToCenterScale = new ScaleTransition(ANIM_DURATION, incomingView);
        incomingToCenterScale.setToX(PROMOTE_TO_CENTER_SCALE);
        incomingToCenterScale.setToY(PROMOTE_TO_CENTER_SCALE);
        incomingToCenterScale.setInterpolator(smooth);

        FadeTransition incomingToCenterOpacity = new FadeTransition(ANIM_DURATION, incomingView);
        incomingToCenterOpacity.setToValue(1.0);
        incomingToCenterOpacity.setInterpolator(smooth);

        TranslateTransition outgoingTargetSlide = new TranslateTransition(ANIM_DURATION, outgoingTargetSlot);
        outgoingTargetSlide.setToX(outerFadeDeltaX);
        outgoingTargetSlide.setInterpolator(smooth);

        FadeTransition outgoingTargetFade = new FadeTransition(ANIM_DURATION, outgoingTargetView);
        outgoingTargetFade.setToValue(0.0);
        outgoingTargetFade.setInterpolator(smooth);

        ParallelTransition transition = new ParallelTransition(centerToSideMove, centerToSideScale, centerToSideOpacity, incomingToCenterMove, incomingToCenterScale, incomingToCenterOpacity, outgoingTargetSlide, outgoingTargetFade);

        transition.setOnFinished(event -> {
            displayCards(previousMini, currentFull, nextMini);
            isAnimating = false;
        });

        transition.play();
    }

    private double computeDeltaX(StackPane from, StackPane to, double fallback) {
        if (from.getScene() != null && to.getScene() != null) {
            Bounds fromBounds = from.localToScene(from.getBoundsInLocal());
            Bounds toBounds = to.localToScene(to.getBoundsInLocal());
            double delta = toBounds.getCenterX() - fromBounds.getCenterX();
            if (Math.abs(delta) > 1.0) {
                return delta;
            }
        }
        return fallback;
    }

    private void resetViewOrder() {
        leftSlot.setViewOrder(0);
        centerSlot.setViewOrder(-1);
        rightSlot.setViewOrder(0);
    }

    private void resetTransforms() {
        leftSlot.setTranslateX(0);
        centerSlot.setTranslateX(0);
        rightSlot.setTranslateX(0);

        leftImageView.setScaleX(SIDE_SCALE);
        leftImageView.setScaleY(SIDE_SCALE);
        leftImageView.setOpacity(SIDE_OPACITY);

        centerImageView.setScaleX(1.0);
        centerImageView.setScaleY(1.0);
        centerImageView.setOpacity(1.0);

        rightImageView.setScaleX(SIDE_SCALE);
        rightImageView.setScaleY(SIDE_SCALE);
        rightImageView.setOpacity(SIDE_OPACITY);

        resetViewOrder();
    }
}
