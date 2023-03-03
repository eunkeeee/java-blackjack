package domain.card;

import domain.card.shuffler.CardsShuffler;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

public class Cards {

    private final Stack<Card> cards;

    public Cards(CardsShuffler shuffler) {
        this.cards = shuffler.shuffleCards(initializeCards());
    }

    private static Stack<Card> initializeCards() {
        Stack<Card> cards = new Stack<>();
        for (Value value : Value.values()) {
            for (Shape shape : Shape.values()) {
                cards.push(new Card(value.getValue(), shape.getShape()));
            }
        }
        return cards;
    }

    public List<Card> giveInitialCards() {
        return new ArrayList<>(List.of(cards.pop(), cards.pop()));
    }

    public boolean contains(final Card card) {
        return cards.contains(card);
    }

    public Card getCard() {
        try {
            return cards.pop();
        } catch (EmptyStackException exception) {
            throw new EmptyStackException();
        }
    }
}
