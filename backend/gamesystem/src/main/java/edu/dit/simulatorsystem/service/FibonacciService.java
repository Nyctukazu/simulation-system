package edu.dit.simulatorsystem.service;

import java.lang.Math;

import edu.dit.simulatorsystem.service.utilities.FibonacciVerifiable;
import edu.dit.simulatorsystem.service.utilities.MultiplyEngine;

public class FibonacciService implements FibonacciVerifiable{
     
    private final MultiplyEngine multiply = new MultiplyEngine();

    @Override
    public boolean isFibonacci(double num1){
        double val1 = Math.sqrt(multiply.product(5, multiply.product(num1)) + 4);
        double val2 = Math.sqrt(multiply.product(5, multiply.product(num1)) - 4);

        return (val1 % 1 == 0) || (val2 % 1 == 0);    
    }
}
