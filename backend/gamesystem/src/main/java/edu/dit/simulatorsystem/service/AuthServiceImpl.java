package edu.dit.simulatorsystem.service;

import edu.dit.simulatorsystem.dto.AuthRequest;
import edu.dit.simulatorsystem.model.entities.UserModel;
import edu.dit.simulatorsystem.model.enums.AccountType;
import edu.dit.simulatorsystem.repository.AuthRepository;
import edu.dit.simulatorsystem.service.utilities.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private AuthRepository authRepository;

    @Override
    public boolean passValidation(String password) {

        if (password != null && password.length() >= 8 && password.length() <= 15 && !password.contains(" ")) {
            String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[-+_!@#$%^&*.,?]).+$";

            return password.matches(regex);
        }
        return false;
        
    }

    @Override 
    public boolean isUsernameUnique(String username) {
        return !authRepository.existsByUsername(username);
    }

    @Override
    public boolean authenticate(AuthRequest request) {
        if (request == null) {
            return false;
        }

        boolean validPassword = passValidation(request.password());
        boolean uniqueUser = isUsernameUnique(request.username());
        boolean success = validPassword && uniqueUser;

        if(success) {
            UserModel newUser = new UserModel();
            newUser.setUsername(request.username());
            newUser.setPassword(request.password());
            newUser.setIsSuccessful(true);

            try {
                newUser.setAccountType(AccountType.valueOf(request.accountType().toUpperCase()));
            } catch (IllegalArgumentException | NullPointerException e) {
                newUser.setAccountType(AccountType.CLIENT);
            }

            authRepository.save(newUser);
        }
        return success;
    }
}
