package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Shape;
import domain.card.Value;
import domain.shuffler.FixedCardsShuffler;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("딜러는 카드를 추가로 받고, 점수를 계산할 수 있다.")
    @Test
    void fillCardsTest() {
        List<Card> initialCards = new ArrayList<>(
                List.of(new Card(Value.THREE, Shape.SPADE), new Card(Value.KING, Shape.SPADE)));
        Dealer dealer = new Dealer(initialCards);
        Cards cards = new Cards(new FixedCardsShuffler());

        dealer.receiveCard(cards.getCard());

        assertThat(dealer.calculateScore()).isEqualTo(23);
    }
}
