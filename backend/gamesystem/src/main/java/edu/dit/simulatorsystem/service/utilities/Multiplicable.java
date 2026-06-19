package edu.dit.simulatorsystem.service.utilities;

import java.util.List;

public interface Multiplicable {
    public double product(double num1);
    public double product(double num1, double num2);
    public double product(List<? extends Number> numbers );

}
