package edu.dit.simulatorsystem.model.entities;

import java.util.List;

public abstract class Operation {
    private List<Integer> sequence;
    private int arraySize;

    // Getters & Setters
    public void setSequence(List<Integer> sequence) { this.sequence = sequence; }
    public List<Integer> getSequence() { return sequence; }

    public void setArraySize(int arraySize) { this.arraySize = arraySize; }
    public int getArraySize() { return arraySize; }

}
