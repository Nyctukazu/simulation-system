package edu.dit.simulatorsystem.service.utilities;

import edu.dit.simulatorsystem.dto.MovesResponse;
import edu.dit.simulatorsystem.dto.SettingRequest;
import edu.dit.simulatorsystem.model.entities.JackEnPoyModel;
import edu.dit.simulatorsystem.model.enums.MovesChoices;
import edu.dit.simulatorsystem.model.enums.RoundResult;

import java.util.List;

public interface JackEnPoyService {
    JackEnPoyModel setPlayAttempts(SettingRequest request);
    JackEnPoyModel getComputerMove();
    void startGame();
    MovesChoices computerTurn();
    RoundResult evaluateRound();
    RoundResult evaluateFinalWinner();
    boolean isMatchSeriesComplete();
    MovesResponse playRound(MovesChoices playerMove);
    List<RoundResult> getGameLogs();
}
