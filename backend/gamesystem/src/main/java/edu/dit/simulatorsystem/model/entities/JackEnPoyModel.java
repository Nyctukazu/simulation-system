package edu.dit.simulatorsystem.model.entities;

import java.util.List;

import edu.dit.simulatorsystem.model.enums.MovesChoices;
import edu.dit.simulatorsystem.model.enums.RoundResult;

public class JackEnPoyModel {
    private String playerName;
    private MovesChoices playerMove;
    private MovesChoices computerMove;
    private List<RoundResult> resultLogs;
    private int arraySize;
    private RoundResult initialIsVictory;
    private RoundResult finalIsVictory;

    // Getters & Setters
    public void setPlayerName(String playerName) { this.playerName = playerName; }
    public String getPlayerName() { return playerName; }

    public void setPlayerMove(MovesChoices playerMove) { this.playerMove = playerMove; }
    public MovesChoices getPlayerMove() { return playerMove; }

    public void setComputerMove(MovesChoices computerMove) { this.computerMove = computerMove; }
    public MovesChoices getComputerMove() { return computerMove; }

    public void setResultLogs(List<RoundResult> resultLogs) {this.resultLogs = resultLogs; }
    public List<RoundResult> getResultLogs() { return resultLogs; }

    public void setArraySize(int arraySize) { this.arraySize = arraySize; }
    public int getArraySize() { return arraySize; }

    public void setInitialIsVictory(RoundResult initialIsVictory) {this.initialIsVictory = initialIsVictory; }
    public RoundResult getInitialIsVictory() { return initialIsVictory; }

    public void setFinalIsVictory(RoundResult finalIsVictory) {this.finalIsVictory = finalIsVictory; }
    public RoundResult getFinalIsVictory() { return finalIsVictory; }
    
    
}
