package edu.dit.simulatorsystem.model.entities;

public class FibonacciModel extends Operation {

    private double checkedNumber;
    private boolean isFibonacci;

    public FibonacciModel() {}

    // Getters & Setters
    public void setIsFibonacci(boolean isFibonacci) { this.isFibonacci = isFibonacci; }
    public boolean getIsFibonacci() { return isFibonacci; }

    public void setCheckedNumber(double checkedNumber) { this.checkedNumber = checkedNumber; }
        public double getCheckedNumber() { return checkedNumber; }
}
