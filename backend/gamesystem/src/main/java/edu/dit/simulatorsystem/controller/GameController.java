package edu.dit.simulatorsystem.controller;

import org.springframework.web.bind.annotation.RestController;

import edu.dit.simulatorsystem.dto.GameRequest;
import edu.dit.simulatorsystem.dto.GameResponse;
import edu.dit.simulatorsystem.service.GameServiceImpl;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/games")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174"})
public class GameController {
    
    private final GameServiceImpl gameService;

    public GameController(GameServiceImpl gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public ResponseEntity<List<GameResponse>> getAllGames() {
        List<GameResponse> games = gameService.getAllGames();
        return ResponseEntity.ok(games);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameResponse> getGameById(@PathVariable Long id) {
        GameResponse game = gameService.getGameById(id);
        return ResponseEntity.ok(game);
    }

    @PostMapping
    public ResponseEntity<GameResponse> createGame(@Valid @RequestBody GameRequest request) {
        GameResponse createdGame = gameService.createGame(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGame);
    }
}
