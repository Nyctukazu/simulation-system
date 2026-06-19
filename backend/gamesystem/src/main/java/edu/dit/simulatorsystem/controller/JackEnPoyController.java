package edu.dit.simulatorsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import edu.dit.simulatorsystem.dto.FinalResultResponse;
import edu.dit.simulatorsystem.dto.MovesRequest;
import edu.dit.simulatorsystem.dto.MovesResponse;
import edu.dit.simulatorsystem.dto.SettingRequest;
import edu.dit.simulatorsystem.model.entities.JackEnPoyModel;
import edu.dit.simulatorsystem.model.enums.RoundResult;
import edu.dit.simulatorsystem.service.utilities.JackEnPoyService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/jack-en-poy")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174"})
public class JackEnPoyController {
    
    @Autowired
    private JackEnPoyService jackEnPoyService;

    @PostMapping("/settings")
    public ResponseEntity<JackEnPoyModel> setPlayAttempts(@RequestBody SettingRequest request) {
        JackEnPoyModel updatedGame = jackEnPoyService.setPlayAttempts(request);
        jackEnPoyService.startGame();
        return ResponseEntity.ok(updatedGame);
    }

    @PostMapping("/move")
    public ResponseEntity<?> makeMove(@RequestBody MovesRequest request) {
        if (jackEnPoyService.isMatchSeriesComplete()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("The game series is already complete. Check the final results.");
        }

        MovesResponse response = jackEnPoyService.playRound(request.playerMove());
        return ResponseEntity.ok(response);

        
    }

    @GetMapping("/final-result")
    public ResponseEntity<FinalResultResponse> getFinalResult() {
        RoundResult finalWinner = jackEnPoyService.evaluateFinalWinner();
        List<RoundResult> logs = jackEnPoyService.getGameLogs();
        FinalResultResponse response = new FinalResultResponse(logs, finalWinner);
        return ResponseEntity.ok(response);
    }


}
