package hr.spring.race.application.command.service.service;

import hr.spring.race.application.command.service.Repository.RaceRepository;
import hr.spring.race.application.command.service.model.entity.Race;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RaceServiceImpl implements RaceService{

    private final RaceRepository raceRepository;

    @Autowired
    public RaceServiceImpl(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    @Override
    public Race createRace(Race race) {
        return raceRepository.save(race);
    }

    @Override
    public Race updateRace(UUID id, Race race) {
        if (raceRepository.existsById(id)) {
            race.setId(id);
            return raceRepository.save(race);
        }
        return null;
    }

    @Override
    public Boolean deleteRace(UUID id) {
        if (raceRepository.existsById(id)) {
            raceRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
