package edu.dit.simulatorsystem.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import edu.dit.simulatorsystem.dto.GameRequest;
import edu.dit.simulatorsystem.dto.GameResponse;
import edu.dit.simulatorsystem.model.entities.GameInfoModel;
import edu.dit.simulatorsystem.repository.GameRepository;
import edu.dit.simulatorsystem.service.utilities.GameService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GameServiceImpl implements GameService {
    
    private final GameRepository gameRepo;

    public GameServiceImpl(GameRepository gameRepo) {
        this.gameRepo = gameRepo;
    }

    @Override
    @Transactional
    public GameResponse createGame(GameRequest request) {
        GameInfoModel game = new GameInfoModel();
        game.setGameName(request.gameName());
        game.setDescription(request.description());
        game.setGameClass(request.gameClass());

        GameInfoModel savedGame = gameRepo.save(game);

        return mapToResponse(savedGame);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GameResponse> getAllGames() {
        return gameRepo.findAll()
            .stream()
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public GameResponse getGameById(Long id) {
        GameInfoModel game = gameRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Game not found with id: " + id));
        return mapToResponse(game);
    }

    private GameResponse mapToResponse(GameInfoModel game) {
        return new GameResponse(
            game.getId(),
            game.getGameName(),
            game.getDescription(),
            game.getGameClass()
        );
    }
}
