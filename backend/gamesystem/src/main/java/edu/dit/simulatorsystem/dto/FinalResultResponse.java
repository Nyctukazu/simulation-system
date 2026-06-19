package edu.dit.simulatorsystem.dto;

import java.util.List;

import edu.dit.simulatorsystem.model.enums.RoundResult;

public record FinalResultResponse(
    List<RoundResult> resultLogs,
    RoundResult finalIsVictory
) {}
