package hr.spring.race.application.command.service.model.entity;


import hr.spring.race.application.command.service.model.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

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
    @NotBlank(message = "Email is required.")
    @Email(message = "Email should be valid.")
    @Size(max = 255, message = "Email must be 255 characters or less.")
    @Column(nullable = false, unique = true)
    private String email;
    @NotNull(message = "Date of birth is required.")
    @Past(message = "Date of birth must be in the past.")
    @Column(nullable = false)
    private LocalDate dob;
    private Role role;
}
