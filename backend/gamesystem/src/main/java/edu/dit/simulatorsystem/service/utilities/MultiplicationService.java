package edu.dit.simulatorsystem.service.utilities;

import edu.dit.simulatorsystem.dto.MultiplicationRequest;
import edu.dit.simulatorsystem.dto.MultiplicationResponse;

public interface MultiplicationService {
    public MultiplicationResponse calculateProduct(MultiplicationRequest request);
}
