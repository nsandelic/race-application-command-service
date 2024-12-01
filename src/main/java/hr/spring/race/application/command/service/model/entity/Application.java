package hr.spring.race.application.command.service.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "application")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotBlank(message = "First name is required.")
    @Size(max = 255, message = "First name must be 255 characters or less.")
    @Column(nullable = false)
    private String firstName;
    @NotBlank(message = "Last name is required.")
    @Size(max = 255, message = "Last name must be 255 characters or less.")
    @Column(nullable = false)
    private String lastName;
    @Size(max = 255, message = "Club name must be 255 characters or less.")
    @Column
    private String club;
    @NotNull(message = "Race ID is required.")
    @Column(nullable = false)
    private UUID raceId;

}
