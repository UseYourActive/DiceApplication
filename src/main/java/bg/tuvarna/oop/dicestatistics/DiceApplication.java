package bg.tuvarna.oop.dicestatistics;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "bg.tuvarna.oop")
@EntityScan(basePackages = "bg.tuvarna.oop.persistence.entities")
@EnableJpaRepositories(basePackages = "bg.tuvarna.oop.persistence.repositories")
public class DiceApplication {
    public static void main(String[] args) {
        Application.launch(JavaFxApplication.class, args);
    }
}
