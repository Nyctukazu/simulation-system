package edu.dit.simulatorsystem.dto;

import edu.dit.simulatorsystem.model.enums.MovesChoices;

public record MovesResponse(
    MovesChoices computerMove
) {}
