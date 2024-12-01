package hr.spring.race.application.command.service.repository;

import hr.spring.race.application.command.service.model.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApplicationRepository extends JpaRepository<Application, UUID> {

}
