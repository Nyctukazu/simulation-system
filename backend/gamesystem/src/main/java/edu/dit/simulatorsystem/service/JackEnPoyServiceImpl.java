package edu.dit.simulatorsystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.dit.simulatorsystem.dto.MovesResponse;
import edu.dit.simulatorsystem.dto.SettingRequest;
import edu.dit.simulatorsystem.model.entities.JackEnPoyModel;
import edu.dit.simulatorsystem.model.enums.MovesChoices;
import edu.dit.simulatorsystem.model.enums.RoundResult;
import edu.dit.simulatorsystem.service.utilities.JackEnPoyService;

public class JackEnPoyServiceImpl implements JackEnPoyService {

    private JackEnPoyModel currentGame;
    private final Random random = new Random();

    @Override
    public JackEnPoyModel setPlayAttempts(SettingRequest request) {
        int size = request.arraySize();

        if(size <= 0) {
            throw new IllegalArgumentException("Array size must be greater than 0");
        }

        this.currentGame = new JackEnPoyModel();
        this.currentGame.setPlayerName(request.playerName());
        this.currentGame.setArraySize(request.arraySize());
        this.currentGame.setResultLogs(new ArrayList<>());

        return this.currentGame;
    }

    @Override
    public JackEnPoyModel getComputerMove() {
        if (currentGame == null) {
            throw new IllegalStateException("Game has not been initialized yet.");
        }
        
        MovesChoices compMove = computerTurn();
        currentGame.setComputerMove(compMove);
        return currentGame;
    }

    @Override
    public void startGame() {
        if (currentGame != null) {
            currentGame.getResultLogs().clear();
            currentGame.setInitialIsVictory(RoundResult.TIE);
            currentGame.setFinalIsVictory(RoundResult.TIE);
        }
    }

    @Override
    public MovesChoices computerTurn() {
        MovesChoices[] moves = MovesChoices.values();
        int randomIndex = random.nextInt(moves.length);
        return moves[randomIndex];
    }

    @Override
    public RoundResult evaluateRound() {
        MovesChoices player = currentGame.getPlayerMove();
        MovesChoices computer = currentGame.getComputerMove();

        if (player == computer) {
            currentGame.getResultLogs().add(RoundResult.TIE);
            return RoundResult.TIE;
        }

        boolean won = (player == MovesChoices.ROCK && computer == MovesChoices.SCISSOR) ||
                    (player == MovesChoices.PAPER && computer == MovesChoices.ROCK) ||
                    (player == MovesChoices.SCISSOR && computer == MovesChoices.PAPER);

        if (won) {
            currentGame.getResultLogs().add(RoundResult.WIN);
            return RoundResult.WIN;
        } else {
            currentGame.getResultLogs().add(RoundResult.LOSS);
            return RoundResult.LOSS;
        }
    }

    @Override
    public RoundResult evaluateFinalWinner() {
        if (currentGame == null) {
            throw new IllegalStateException("Game has not been initialized yet.");
        }

        int playerWins = 0;
        int computerWins = 0;

        for (RoundResult result: currentGame.getResultLogs()) {
            if (result == RoundResult.WIN) {
                playerWins++;
            } else if (result == RoundResult.LOSS) {
                computerWins++;
            }
        }

        RoundResult finalResult;
        if (playerWins > computerWins) {
            finalResult = RoundResult.WIN;
        } else if (computerWins > playerWins) {
            finalResult = RoundResult.LOSS;
        } else {
            finalResult = RoundResult.TIE;
        }

        currentGame.setFinalIsVictory(finalResult);
        return finalResult;
    }

    public boolean isMatchSeriesComplete() {
        if (currentGame == null) {
            return false;
        }

        return currentGame.getResultLogs().size() >= currentGame.getArraySize();
    }

    @Override
    public MovesResponse playRound(MovesChoices playerMove) {
        if (currentGame == null) {
            throw new IllegalStateException("Game has not been initialized yet.");
        }

        currentGame.setPlayerMove(playerMove);

        MovesChoices compMove = computerTurn();
        currentGame.setComputerMove(compMove);
        RoundResult result = evaluateRound();
        return new MovesResponse(
            playerMove,
            compMove,
            result,
            isMatchSeriesComplete()
        );
    }

    @Override
    public List<RoundResult> getGameLogs() {
        if (currentGame == null) {
            return new ArrayList<>();
        }
        return currentGame.getResultLogs();
    }
    
}
