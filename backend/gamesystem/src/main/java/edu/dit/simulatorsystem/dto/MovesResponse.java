package edu.dit.simulatorsystem.dto;

import edu.dit.simulatorsystem.model.enums.MovesChoices;
import edu.dit.simulatorsystem.model.enums.RoundResult;

public record MovesResponse(
    MovesChoices playerMove,
    MovesChoices computerMove,
    RoundResult initialIsVictory,
    boolean isRoundComplete
) {}
