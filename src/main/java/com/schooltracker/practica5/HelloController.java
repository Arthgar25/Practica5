package com.schooltracker.practica5;

import com.schooltracker.practica5.Model.Carta;
import com.schooltracker.practica5.Model.CartaModel;
import com.schooltracker.practica5.Model.CircularList;
import com.schooltracker.practica5.View.Attribute;
import com.schooltracker.practica5.View.CardCarouselView;
import com.schooltracker.practica5.View.CardImageLoader;
import com.schooltracker.practica5.View.CardLevel;
import com.schooltracker.practica5.View.CartaView;
import com.schooltracker.practica5.View.MainScreen;
import com.schooltracker.practica5.View.SideBar;
import javafx.scene.image.Image;

public class HelloController {
    private final CartaModel model;
    private final MainScreen view;
    private final CartaView cartaView;
    private CircularList<Carta> activeCards;
    private int currentIndex = 0;

    public HelloController(CartaModel model, MainScreen view) {
        this.model = model;
        this.view = view;
        this.cartaView = new CartaView();
        this.activeCards = model.showAllCards();

        this.cartaView.setOnPreviousClicked(this::handlePreviousCard);
        this.cartaView.setOnNextClicked(this::handleNextCard);
        bindSideBarActions(this.view.getSideBar());

        this.view.setCenter(cartaView);
        loadInitialCard();
    }

    private void bindSideBarActions(SideBar sideBar) {
        sideBar.setShowAllCardsAction(e -> setActiveCards(model.showAllCards()));

        sideBar.setShowByLevelAction(e -> {
            CardLevel level = sideBar.getSelectedLevel();
            if (level != null) {
                setActiveCards(model.showByLevel(level.getLabel()));
            }
        });

        sideBar.setShowByAttributeAction(e -> {
            Attribute attribute = sideBar.getSelectedAttribute();
            if (attribute != null) {
                setActiveCards(model.showByAttribute(attribute.getLabel()));
            }
        });

        sideBar.setShowByAttackPointsAction(e ->
                setActiveCards(model.showByAttackPoints(sideBar.isAttackLowToHigh()))
        );

        sideBar.setShowByDefensePointsAction(e ->
                setActiveCards(model.showByDefensePoints(sideBar.isDefenseLowToHigh()))
        );
    }

    public void setActiveCards(CircularList<Carta> cards) {
        this.activeCards = (cards != null) ? cards : new CircularList<>();
        this.currentIndex = 0;
        loadInitialCard();
    }

    private void loadInitialCard() {
        if (activeCards == null || activeCards.isEmpty()) {
            cartaView.showInitialCard(
                    null, null, null,
                    "No cards found", "", "", "", "", "", "", "",
                    0, 0
            );
            return;
        }

        currentIndex = 0;
        int size = activeCards.size();
        Carta prevCard = activeCards.get((currentIndex - 1 + size) % size);
        Carta currentCard = activeCards.get(currentIndex);
        Carta nextCard = activeCards.get((currentIndex + 1) % size);

        Image prevMini = (size > 1)
                ? CardImageLoader.loadMiniatureImage(prevCard.getCardNumber(), prevCard.getCardName())
                : null;
        Image currFull = CardImageLoader.loadFullCardImage(currentCard.getCardNumber(), currentCard.getCardName());
        Image nextMini = (size > 1)
                ? CardImageLoader.loadMiniatureImage(nextCard.getCardNumber(), nextCard.getCardName())
                : null;

        cartaView.showInitialCard(
                prevMini, currFull, nextMini,
                currentCard.getCardName(),
                currentCard.getRarity(),
                currentCard.getAttributeType(),
                currentCard.getLevelRank(),
                currentCard.getRaceSubtype(),
                currentCard.getAttack(),
                currentCard.getDefense(),
                currentCard.getDescription(),
                currentIndex + 1,
                size
        );
    }

    private void handlePreviousCard() {
        if (activeCards == null || activeCards.size() <= 1) {
            return;
        }

        int size = activeCards.size();
        currentIndex = (currentIndex - 1 + size) % size;
        updateViewWithDirection(activeCards, CardCarouselView.SlideDirection.LEFT);
    }

    private void handleNextCard() {
        if (activeCards == null || activeCards.size() <= 1) {
            return;
        }

        int size = activeCards.size();
        currentIndex = (currentIndex + 1) % size;
        updateViewWithDirection(activeCards, CardCarouselView.SlideDirection.RIGHT);
    }

    private void updateViewWithDirection(CircularList<Carta> cards, CardCarouselView.SlideDirection direction) {
        int size = cards.size();
        Carta prevCard = cards.get((currentIndex - 1 + size) % size);
        Carta currentCard = cards.get(currentIndex);
        Carta nextCard = cards.get((currentIndex + 1) % size);

        Image prevMini = (size > 1)
                ? CardImageLoader.loadMiniatureImage(prevCard.getCardNumber(), prevCard.getCardName())
                : null;
        Image currFull = CardImageLoader.loadFullCardImage(currentCard.getCardNumber(), currentCard.getCardName());
        Image nextMini = (size > 1)
                ? CardImageLoader.loadMiniatureImage(nextCard.getCardNumber(), nextCard.getCardName())
                : null;

        cartaView.navigateToCard(
                prevMini, currFull, nextMini, direction,
                currentCard.getCardName(),
                currentCard.getRarity(),
                currentCard.getAttributeType(),
                currentCard.getLevelRank(),
                currentCard.getRaceSubtype(),
                currentCard.getAttack(),
                currentCard.getDefense(),
                currentCard.getDescription(),
                currentIndex + 1,
                size
        );
    }
}