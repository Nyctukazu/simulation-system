package edu.dit.simulatorsystem.dto;

import edu.dit.simulatorsystem.model.enums.GameClass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record GameRequest( 

    @NotBlank(message = "Game name cannot be empty")
    @Size(max = 100, message = "Game name must be under 100 characters")
    String gameName,

    @NotBlank(message = "Description cannot be empty")
    @Size(max = 500, message = "Description must be under 100 characters")
    String description,

    @NotNull(message = "Game class is required")
    GameClass gameClass
) {}
