package edu.dit.simulatorsystem.dto;

import edu.dit.simulatorsystem.model.enums.GameClass;

public record GameResponse(
    Long id,
    String gameName,
    String description,
    GameClass gameClass
) {}
