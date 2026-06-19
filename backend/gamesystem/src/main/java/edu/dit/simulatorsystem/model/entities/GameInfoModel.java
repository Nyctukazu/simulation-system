package edu.dit.simulatorsystem.model.entities;

import edu.dit.simulatorsystem.model.enums.GameClass;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "game_info")
public class GameInfoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String gameName;
    private String description;

    @Enumerated(EnumType.STRING)
    private GameClass gameClass;

    // Getters & Setters
    public void setId(Long id) { this.id = id; }
    public Long getId() { return id; }

    public void setGameName(String gameName) { this.gameName = gameName; }
    public String getGameName() { return gameName; }

    public void setDescription(String description) { this.description = description; }
    public String getDescription() { return description; }

    public void setGameClass(GameClass gameClass) { this.gameClass = gameClass; }
    public GameClass getGameClass() { return gameClass; }
}
