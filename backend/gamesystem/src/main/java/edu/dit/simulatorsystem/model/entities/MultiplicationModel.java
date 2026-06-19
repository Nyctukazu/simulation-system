package edu.dit.simulatorsystem.model.entities;

import java.util.List;

public class MultiplicationModel extends Operation{
    private boolean isSuccessful;

    public MultiplicationModel() {}

    // Getters & Setters
    public boolean isSuccessful() { return isSuccessful; }
    public void setSuccessful(boolean isSuccessful) { this.isSuccessful = isSuccessful; }
}
