package domain.card;

import static domain.Fixtures.KING_CLOVER;
import static domain.Fixtures.KING_SPADE;
import static domain.Fixtures.QUEEN_HEART;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domain.participant.Score;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {

    private static final Cards BUST_CARDS = new Cards()
            .receiveInitialCards(
                    List.of(KING_CLOVER,
                            KING_SPADE))
            .receiveCard(QUEEN_HEART);

    @DisplayName("정확히 2장의 카드를 초기에 받을 수 있다.")
    @Test
    void receiveInitialCardsTest() {
        Cards cards = new Cards();
        assertDoesNotThrow(() -> cards.receiveInitialCards(
                List.of(KING_CLOVER, QUEEN_HEART)));
    }

    @DisplayName("전체의 합을 계산할 수 있다.")
    @Test
    void calculateScoreTest() {
        assertThat(BUST_CARDS.calculateScore()).isEqualTo(new Score(30));
    }

    @DisplayName("전체의 합이 21을 초과하면 Bust이다.")
    @Test
    void isBustTest() {
        assertTrue(BUST_CARDS.isBust());
    }
}
