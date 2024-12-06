package hr.spring.race.application.command.service.repository;

import hr.spring.race.application.command.service.model.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("select u from User u where u.email = :#{#email}")
    User findByEmail(@Param("email") String email);

    Boolean existsUserByEmail(@NotBlank(message = "Email is required.") @Email(message = "Email should be valid.") @Size(max = 255, message = "Email must be 255 characters or less.") String email);

}
