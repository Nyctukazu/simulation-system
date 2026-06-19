package edu.dit.simulatorsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.dit.simulatorsystem.model.entities.UserModel;

@Repository
public interface AuthRepository extends JpaRepository<UserModel, Long>{
    boolean existsByUsername(String username);
}
