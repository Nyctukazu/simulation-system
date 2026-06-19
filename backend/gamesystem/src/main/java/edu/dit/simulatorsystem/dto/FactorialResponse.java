package edu.dit.simulatorsystem.dto;

import java.util.List;

public record FactorialResponse(
    int inputNumber,
    long product,
    List<Integer> list
) {}
