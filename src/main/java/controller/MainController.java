package controller;

import domain.PlayerCommand;
import domain.card.Cards;
import domain.card.shuffler.RandomCardsShuffler;
import domain.participant.Dealer;
import domain.participant.Participants;
import domain.participant.Player;
import view.InputView;
import view.OutputView;

public class MainController {

    private final InputView inputView;
    private final OutputView outputView;

    public MainController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Cards cards = new Cards(new RandomCardsShuffler());

        Participants participants = new Participants(inputView.readPlayerNames(), cards);
        outputView.printInitialMessage(participants.getNames());

        outputView.printAllParticipantsState(participants);

        for (Player player : participants.getPlayers()) {
            PlayerCommand command = PlayerCommand.HIT;
            while (command.isHit()) {
                command = PlayerCommand.from(inputView.readHit(player.getName()));
                player.receiveAdditionalCard(command, cards);
                outputView.printOneParticipantState(player);
            }
        }

        Dealer dealer = participants.getDealer();
        while (dealer.needsFillCards()) {
            outputView.printFillDealerCards();
            participants.getDealer().fillCards(cards);
        }

        outputView.printAllParticipantsState(participants);

        outputView.printFinalResult(participants);
    }
}
