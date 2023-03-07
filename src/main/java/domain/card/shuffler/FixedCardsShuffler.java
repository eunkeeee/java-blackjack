package domain.card.shuffler;

import domain.card.Card;
import java.util.Stack;

public final class FixedCardsShuffler implements CardsShuffler {
    @Override
    public Stack<Card> shuffleCards(final Stack<Card> cards) {
        return cards;
    }
}
