package domain.participant;

import domain.card.CardDeck;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class Participants {

    private static final int MIN_SIZE_EXCLUSIVE = 1;
    private static final int MAX_SIZE_EXCLUSIVE = 7;

    private final List<Player> players;
    private final Dealer dealer;

    public Participants(List<String> names) {
        validateSize(names);
        this.players = names.stream()
                .map(Name::new)
                .map(Player::new)
                .collect(Collectors.toList());
        this.dealer = new Dealer();
    }

    public void distributeInitialCards(CardDeck cardDeck) {
        players.forEach(player -> player.receiveInitialCards(cardDeck.giveInitialCards()));
        dealer.receiveInitialCards(cardDeck.giveInitialCards());
    }

    private void validateSize(final List<String> names) {
        if (names.size() < MIN_SIZE_EXCLUSIVE || names.size() > MAX_SIZE_EXCLUSIVE) {
            throw new IllegalArgumentException("전체 플레이어의 수는 1명 이상 7명 이하여야 합니다!");
        }
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
