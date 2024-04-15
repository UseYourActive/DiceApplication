package bg.tuvarna.oop.persistence.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;
    @Length(max=60, message = "Fist name should be max 60 characters")
    private String lastName;
    @Length(max=60, message = "Fist name should be max 60 characters")
    private String firstName;
    @Length(max=10, message = "EGN should be max 10 characters")
    private String egn;
}
