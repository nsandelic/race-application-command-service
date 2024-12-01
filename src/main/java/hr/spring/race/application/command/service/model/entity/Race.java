package hr.spring.race.application.command.service.model.entity;

import hr.spring.race.application.command.service.util.enums.RaceDistance;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "race")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Race {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotBlank(message = "Race name is required.")
    @Size(max = 255, message = "Race name must be 255 characters or less.")
    @Column(nullable = false)
    private String name;
    @NotNull(message = "Race distance is required.")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RaceDistance distance;


}
