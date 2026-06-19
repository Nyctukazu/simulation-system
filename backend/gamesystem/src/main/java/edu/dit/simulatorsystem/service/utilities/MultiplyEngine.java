package edu.dit.simulatorsystem.service.utilities;

import java.util.List;

/**
 * Provides concrete mathematical operations for multiplying numeric values.
 * This class implements the {@link Multiplicable} interface to serve as the 
 * core multiplication engine within the simulator system.
 * 
 * @author Laurence Ryan C. Cruz
 * @version 1.0
 */
public class MultiplyEngine implements Multiplicable {


    /**
     * Calculates the product of itself
     * 
     * @param num the number to multiply itself once
     * @return the result of multiplying {@code num} itself
     */
    @Override
    public double product(double num) {
        return num * num;
    }
    
    /**
     * Calculates the product of two primitive double numbers.
     * 
     * @param num1 the first number to multiply
     * @param num2 the second number to multiply
     * @return the result of multiplying {@code num1} and {@code num2}
     */
    @Override
    public double product(double num1, double num2) {
        return num1 * num2;
    }

    /**
     * Calculates the product of a list of numbers.
     * <p>
     * Null elements within the list are safely skipped. If the list is 
     * null or completely empty, the method returns 0.0.
     * </p>
     * 
     * @param numbers a list containing objects that extend {@link Number}
     * @return the product of all valid numbers in the list, or 0.0 if empty/null
     */
    @Override
    public double product(List<? extends Number> numbers ) {
        if (numbers == null || numbers.isEmpty()) {
            return 0.0;
        }
        double result = 1.0;
        for (Number number : numbers) {
            if (number == null) {
                continue;
            }
            result *= number.doubleValue();
        }
        return result;
    }
    
}
