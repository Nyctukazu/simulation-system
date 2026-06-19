package edu.dit.simulatorsystem.service.utilities;

import java.util.List;

import edu.dit.simulatorsystem.dto.GameRequest;
import edu.dit.simulatorsystem.dto.GameResponse;

public interface GameService {

    GameResponse createGame(GameRequest request);
    List<GameResponse> getAllGames();
    GameResponse getGameById(Long id);
}
