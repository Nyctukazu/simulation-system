package edu.dit.simulatorsystem.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import edu.dit.simulatorsystem.dto.FactorialRequest;
import edu.dit.simulatorsystem.dto.FactorialResponse;
import edu.dit.simulatorsystem.service.utilities.FactorialVerifiable;

@Service
public class FactorialServiceImpl implements FactorialVerifiable {

    @Override
    public FactorialResponse calculateFactorial(FactorialRequest request) {
        int input = request.num1();
        List<Integer> steps = new ArrayList<>();
        if (input < 0) {
            throw new IllegalArgumentException("Factorial is not defined for negative numbers.");
        }

        long product = 1;
        
        for (int i = 1; i <= input; i++) {
            steps.add(i);
            product *= i;
        }

        return new FactorialResponse(input, product, steps);
    }
}
