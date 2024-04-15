package bg.tuvarna.oop.persistence.repositories;

import bg.tuvarna.oop.persistence.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}