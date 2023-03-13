package domain.money;

import domain.participant.Player;
import domain.result.WinningResult;
import domain.result.WinningStatus;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Bets {
    private final HashMap<Player, Money> bets;

    public Bets() {
        this.bets = new LinkedHashMap<>();
    }

    public void addBet(final Player player, final int amount) {
        validateBetAmount(amount);
        bets.put(player, new Money(amount));
    }

    private static void validateBetAmount(final int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("베팅 금액은 1원 이상부터 가능합니다.");
        }
    }

    public Map<Player, Money> calculatePlayersProfit(WinningResult winningResult) {
        Map<Player, Money> weightedBets = new LinkedHashMap<>();
        Map<Player, WinningStatus> winningStatusMap = winningResult.getPlayersResult();
        for (Player player : winningStatusMap.keySet()) {
            BigDecimal weight = player.profitRate();
            BigDecimal statusMultiplier = winningStatusMap.get(player).multiplier();
            Money weightedBet = this.bets.get(player)
                    .multiply(weight)
                    .multiply(statusMultiplier);
            weightedBets.put(player, weightedBet);
        }
        return weightedBets;
    }

    public Money calculateDealerProfit(WinningResult winningResult) {
        Map<Player, Money> playersProfit = calculatePlayersProfit(winningResult);
        int profit = playersProfit.values().stream()
                .mapToInt(Money::getAmount)
                .sum();
        return new Money(profit).multiply(BigDecimal.valueOf(-1));
    }
}
