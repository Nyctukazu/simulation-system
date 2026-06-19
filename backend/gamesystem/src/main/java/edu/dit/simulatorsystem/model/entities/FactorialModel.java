package edu.dit.simulatorsystem.model.entities;

import java.util.List;

public class FactorialModel extends Operation {
    private long productResult;
    
    public FactorialModel() {}

    // Getters & Setters
    public long getProductResult() { return productResult; }
    public void setProductResult(long productResult) { this.productResult = productResult; }

}
