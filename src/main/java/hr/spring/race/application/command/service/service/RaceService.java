package hr.spring.race.application.command.service.service;

import hr.spring.race.application.command.service.model.entity.Race;

import java.util.UUID;

public interface RaceService {

    Race createRace(Race race);
    Race updateRace(UUID id, Race race);
    Boolean deleteRace(UUID id);
}
