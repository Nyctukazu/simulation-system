package edu.dit.simulatorsystem.model.entities;

import edu.dit.simulatorsystem.model.enums.AccountType;

public abstract class User {
    private String password;
    private String username;
    private AccountType accountType;

    // Getters & Setters
    public void setPassword(String password) { this.password = password; }
    public String getPassword() { return password; }

    public void setUsername(String username) { this.username = username; }
    public String getUsername() { return username; }

    public void setAccountType(AccountType accountType) { this.accountType = accountType; }
    public AccountType getAccountType() { return accountType; }
    
}
