package com.schooltracker.practica5;

import com.schooltracker.practica5.Model.Carta;
import com.schooltracker.practica5.Model.CartaModel;
import com.schooltracker.practica5.Model.CircularList;
import com.schooltracker.practica5.View.CartaView;
import com.schooltracker.practica5.View.MainScreen;

public class HelloController {
    private final CartaModel model;
    private final MainScreen view;
    private final CartaView cartaView;

    public HelloController(CartaModel model, MainScreen view) {
        this.model = model;
        this.view = view;
        this.cartaView = new CartaView();

        loadFirstCard();
        this.view.setCenter(cartaView);
    }

    private void loadFirstCard() {
        CircularList<Carta> cards = model.showAllCards();
        if (!cards.isEmpty()) {
            Carta firstCard = cards.get(0);
            cartaView.setCardData(
                    firstCard.getCardName(),
                    firstCard.getRarity(),
                    firstCard.getAttributeType(),
                    firstCard.getLevelRank(),
                    firstCard.getRaceSubtype(),
                    firstCard.getAttack(),
                    firstCard.getDefense(),
                    firstCard.getDescription()
            );
        }
    }
}