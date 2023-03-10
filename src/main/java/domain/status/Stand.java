package domain.status;

import domain.card.Cards;
import java.math.BigDecimal;

public class Stand implements EndStatus {

    private final Cards cards;

    public Stand(final Cards cards) {
        this.cards = cards;
    }

    @Override
    public BigDecimal profitWeight() {
        return BigDecimal.valueOf(1);
    }
}
