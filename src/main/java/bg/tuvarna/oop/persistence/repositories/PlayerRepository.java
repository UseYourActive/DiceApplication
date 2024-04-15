package bg.tuvarna.oop.persistence.repositories;

import bg.tuvarna.oop.persistence.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}