package domain.result;

import domain.participant.Dealer;
import domain.participant.Participants;
import domain.participant.Player;
import domain.participant.Score;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public final class WinningResult {

    private final Map<Player, WinningStatus> playersResult = new HashMap<>();

    public WinningResult(final Participants participants) {
        computeWinningResult(participants);
    }

    private void computeWinningResult(final Participants participants) {
        Dealer dealer = participants.getDealer();
        for (Player player : participants.getPlayers()) {
            WinningStatus playerWinningStatus = compete(player, dealer);
            playersResult.put(player, playerWinningStatus);
        }
    }

    private WinningStatus compete(final Player player, final Dealer dealer) {
        if (dealer.isBust()) {
            return statusWhenDealerBust(player);
        }
        return statusWhenDealerNotBust(player, dealer);
    }

    private WinningStatus statusWhenDealerBust(final Player player) {
        if (player.isBust()) {
            return WinningStatus.DRAW;
        }
        return WinningStatus.WIN;
    }

    private WinningStatus statusWhenDealerNotBust(final Player player, final Dealer dealer) {
        Score playerScore = player.calculateScore();
        Score dealerScore = dealer.calculateScore();
        if (!player.isBust() && playerScore.wins(dealerScore)) {
            return WinningStatus.WIN;
        }
        if (playerScore.draws(dealerScore)) {
            return WinningStatus.DRAW;
        }
        return WinningStatus.LOSE;
    }

    public Map<Player, WinningStatus> getPlayersResult() {
        return Map.copyOf(playersResult);
    }

    public Map<WinningStatus, Integer> getDealerResult() {
        Map<WinningStatus, Integer> dealerResult = new EnumMap<>(WinningStatus.class);
        for (WinningStatus playerWinningStatus : playersResult.values()) {
            WinningStatus winningStatus = playerWinningStatus.reverse();
            dealerResult.put(winningStatus, dealerResult.getOrDefault(winningStatus, 0) + 1);
        }
        return dealerResult;
    }
}
