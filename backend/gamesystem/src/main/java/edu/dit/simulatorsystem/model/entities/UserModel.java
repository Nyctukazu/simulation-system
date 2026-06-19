package edu.dit.simulatorsystem.model.entities;

import edu.dit.simulatorsystem.model.enums.AccountType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String password;
    private String username;
    private boolean isSuccessful;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    public UserModel() {}

    // Getters & Setters
    public void setId(Long id) { this.id = id; }
    public Long getId() { return id; }
    
    public void setPassword(String password) { this.password = password; }
    public String getPassword() { return password; }

    public void setUsername(String username) { this.username = username; }
    public String getUsername() { return username; }

    public void setIsSuccessful(boolean isSuccessful) { this.isSuccessful = isSuccessful; }
    public boolean getIsSuccessful() { return isSuccessful; }

    public void setAccountType(AccountType accountType) { this.accountType = accountType; }
    public AccountType getAccountType() { return accountType; }
    
}
