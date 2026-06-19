package edu.dit.simulatorsystem.service.utilities;

import edu.dit.simulatorsystem.dto.AuthRequest;

public interface AuthService {
    boolean passValidation(String password);
    boolean isUsernameUnique(String username);
    boolean authenticate(AuthRequest request);
}
