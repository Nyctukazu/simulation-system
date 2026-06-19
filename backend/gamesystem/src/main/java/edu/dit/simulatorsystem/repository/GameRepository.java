package edu.dit.simulatorsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.dit.simulatorsystem.model.entities.GameInfoModel;

@Repository
public interface GameRepository extends JpaRepository<GameInfoModel, Long>{
    
}
