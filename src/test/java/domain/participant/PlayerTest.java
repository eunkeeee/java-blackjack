package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Shape;
import domain.card.Value;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    private Player gitJjang;
    private Player kyle;

    @BeforeEach
    void setUp() {
        gitJjang = new Player(new Name("깃짱"));
        List<Card> gitJjangCards = List.of(new Card(Value.KING, Shape.HEART), new Card(Value.THREE, Shape.SPADE));
        gitJjang.receiveInitialCards(gitJjangCards);

        kyle = new Player(new Name("카일"));
        List<Card> kyleCards = List.of(new Card(Value.FOUR, Shape.SPADE), new Card(Value.ACE, Shape.CLOVER));
        kyle.receiveInitialCards(kyleCards);
    }

    @DisplayName("플레이어의 점수를 알 수 있다.")
    @Test
    void calculateScoreTest() {
        assertThat(gitJjang.getScore()).isEqualTo(13);
        assertThat(kyle.getScore()).isEqualTo(15);
    }

    @DisplayName("플레이어는 카드를 추가로 받을지 선택할 수 있다.")
    @Test
    void receiveAdditionalCardTest() {
        gitJjang.receiveCard(new Card(Value.TEN, Shape.HEART));
        assertThat(gitJjang.getScore()).isEqualTo(23);

        kyle.receiveCard(new Card(Value.FOUR, Shape.HEART));
        assertThat(kyle.getScore()).isEqualTo(19);

        kyle.receiveCard(new Card(Value.ACE, Shape.DIAMOND));
        assertThat(kyle.getScore()).isEqualTo(20);

        kyle.receiveCard(new Card(Value.NINE, Shape.DIAMOND));
        assertThat(kyle.getScore()).isEqualTo(19);
    }
}
