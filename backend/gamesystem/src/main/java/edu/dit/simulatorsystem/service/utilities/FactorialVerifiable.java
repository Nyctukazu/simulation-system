package edu.dit.simulatorsystem.service.utilities;

import edu.dit.simulatorsystem.dto.FactorialRequest;
import edu.dit.simulatorsystem.dto.FactorialResponse;

public interface FactorialVerifiable {
    FactorialResponse calculateFactorial(FactorialRequest request);
}
