package view;

import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.participant.Player;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final OutputView instance = new OutputView();

    public static OutputView getInstance() {
        return instance;
    }

    private OutputView() {
    }

    public void printInitialMessage(final List<String> names) {
        String dealerName = names.get(0);
        String playerNames = names.stream().skip(1).collect(Collectors.joining(", "));
        System.out.printf("%s와 %s에게 2장을 나누었습니다." + System.lineSeparator(), dealerName, playerNames);
    }

    public void printAllParticipantsState(final Participants participants) {
        Dealer dealer = participants.getDealer();
        System.out.printf(
                "%s: %s%s" + System.lineSeparator(),
                dealer.getName(), dealer.showInitialCard().getValue(), dealer.showInitialCard().getShape());

        List<Player> players = participants.getPlayers();
        for (Player player : players) {
            printOneParticipantState(player);
        }
        System.out.println();
    }

    public void printOneParticipantState(final Participant participant) {
        String cards = participant.getCards().stream().collect(Collectors.joining(", "));
        System.out.printf("%s카드: %s" + System.lineSeparator(), participant.getName(), cards);
    }

    public void printFillDealerCards() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printFinalResult(final Participants participants) {
        System.out.println("## 최종 승패");
    }
}
