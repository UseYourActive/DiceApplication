package bg.tuvarna.oop.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "rolls")
public class Rolls {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne
    private Game gameId;
    private int number2;
    private int number3;
    private int number4;
    private int number5;
    private int number6;
    private int number7;
    private int number8;
    private int number9;
    private int number10;
    private int number11;
    private int number12;
}
