package edu.dit.simulatorsystem.dto;

import java.util.List;

public record MultiplicationResponse(
    int targetNumber,
    List<TableEntry> tableEntries,
    boolean isSuccessful
) {
    // Nested helper record to make rendering clean on the frontend
    public record TableEntry(
        int base,
        int multiplier,
        int product
    ) {}
}
