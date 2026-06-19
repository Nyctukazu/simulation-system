package edu.dit.simulatorsystem.dto;

public record AuthRequest(
    String password,
    String username,
    String accountType
) {}
