package hr.spring.race.application.command.service.service;

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
        messagingTemplate.convertAndSend("/topic/race-events", "CREATE: " + createdRace.getId());
        return createdRace;
    }

    @Override
    public Race updateRace(UUID id, Race race) {
        if (raceRepository.existsById(id)) {
            race.setId(id);
            Race updatedRace = raceRepository.save(race);
            messagingTemplate.convertAndSend("/topic/race-events", "UPDATE: " + updatedRace.getId());
            return updatedRace;
        }
        return null;
    }

    @Override
    public Boolean deleteRace(UUID id) {
        if (raceRepository.existsById(id)) {
            raceRepository.deleteById(id);
            messagingTemplate.convertAndSend("/topic/race-events", "DELETE: " + id);
            return true;
        }
        return false;
    }
}
