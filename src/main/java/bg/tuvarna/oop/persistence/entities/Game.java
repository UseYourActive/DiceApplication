package bg.tuvarna.oop.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    private Player player1;
    private int player1WinnedRows;

    @ManyToOne
    private Player player2;
    private int player2WinnedRows;
}
