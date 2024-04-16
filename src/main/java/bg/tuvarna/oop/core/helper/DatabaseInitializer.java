package bg.tuvarna.oop.core.helper;

import bg.tuvarna.oop.persistence.entities.Player;
import bg.tuvarna.oop.persistence.repositories.PlayerRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatabaseInitializer implements CommandLineRunner {
    private final PlayerRepository playerRepository;

    @Override
    public void run(String... args) throws Exception {
        if (playerRepository.count() <= 0) {
            Player player1 = Player.builder()
                    .firstName("Pavel")
                    .lastName("Petrov")
                    .egn("0148950648")
                    .build();
            playerRepository.save(player1);

            Player player2 = Player.builder()
                    .firstName("Mihail")
                    .lastName("Simeonov")
                    .egn("0143941615")
                    .build();
            playerRepository.save(player2);
        }
    }
}
