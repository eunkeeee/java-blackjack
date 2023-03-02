package domain.participant;

import domain.card.Card;
import java.util.List;
import java.util.stream.Collectors;

public class Participant {

    private final Name name;
    protected final List<Card> cards;

    public Participant(final Name name, final List<Card> cards) {
        this.name = name;
        this.cards = cards;
    }

    public void receiveCard(Card card) {
        this.cards.add(card);
    }

    public int calculateScore() {
        int totalScore = 0;
        for (Card card : cards) {
            totalScore += card.getScore(totalScore);
        }
        return totalScore;
    }

    public String getName() {
        return name.getName();
    }

    public List<String> getCards() {
        return cards.stream()
                .map(card -> String.format("%s%s", card.getValue(), card.getShape()))
                .collect(Collectors.toList());
    }
}
