package hr.spring.race.application.command.service.service;

import hr.spring.race.application.command.service.model.event.RaceEvent;
import hr.spring.race.application.command.service.repository.RaceRepository;
import hr.spring.race.application.command.service.model.entity.Race;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RaceServiceImpl implements RaceService{

    private final RaceRepository raceRepository;
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public RaceServiceImpl(RaceRepository raceRepository, SimpMessagingTemplate messagingTemplate) {
        this.raceRepository = raceRepository;
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public Race createRace(Race race) {
        Race createdRace = raceRepository.save(race);
        messagingTemplate.convertAndSend("/topic/race-events", new RaceEvent("CREATE", createdRace));
        return createdRace;
    }

    @Override
    public Race updateRace(UUID id, Race race) {
        if (raceRepository.existsById(id)) {
            race.setId(id);
            Race updatedRace = raceRepository.save(race);
            messagingTemplate.convertAndSend("/topic/race-events", new RaceEvent("UPDATE", updatedRace));
            return updatedRace;
        }
        return null;
    }

    @Override
    public Boolean deleteRace(UUID id) {
        if (raceRepository.existsById(id)) {
            Race deletedRace = raceRepository.findById(id).orElse(null);
            raceRepository.deleteById(id);
            messagingTemplate.convertAndSend("/topic/race-events", new RaceEvent("DELETE", deletedRace));
            return true;
        }
        return false;
    }
}
