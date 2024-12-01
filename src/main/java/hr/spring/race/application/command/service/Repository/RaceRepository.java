package hr.spring.race.application.command.service.Repository;

import hr.spring.race.application.command.service.model.entity.Race;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RaceRepository extends JpaRepository<Race, UUID> {

}
