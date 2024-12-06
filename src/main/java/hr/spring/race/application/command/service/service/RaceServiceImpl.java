package hr.spring.race.application.command.service.service;

import hr.spring.race.application.command.service.model.event.RaceEvent;
import hr.spring.race.application.command.service.repository.RaceRepository;
import hr.spring.race.application.command.service.model.entity.Race;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.logging.Logger;

@Service
public class RaceServiceImpl implements RaceService{

    private static final Logger logger = Logger.getLogger(RaceServiceImpl.class.getName());
    private final RaceRepository raceRepository;
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public RaceServiceImpl(RaceRepository raceRepository, SimpMessagingTemplate messagingTemplate) {
        this.raceRepository = raceRepository;
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public Race createRace(Race race) {
        logger.info("RaceServiceImpl - > createRace()");
        Race createdRace = raceRepository.save(race);
        messagingTemplate.convertAndSend("/topic/race-events", new RaceEvent("CREATE", createdRace));
        logger.info("RaceServiceImpl - > Race crated and STOMP event sent");
        return createdRace;
    }

    @Override
    public Race updateRace(UUID id, Race race) {
        logger.info("RaceServiceImpl - > updateRace()");
        if (raceRepository.existsById(id)) {
            race.setId(id);
            Race updatedRace = raceRepository.save(race);
            messagingTemplate.convertAndSend("/topic/race-events", new RaceEvent("UPDATE", updatedRace));
            logger.info("RaceServiceImpl - > Race updated and STOMP event sent");
            return updatedRace;
        }
        logger.info("RaceServiceImpl - > Race not found");
        return null;
    }

    @Override
    public Boolean deleteRace(UUID id) {
        logger.info("RaceServiceImpl - > deleteRace()");
        if (raceRepository.existsById(id)) {
            Race deletedRace = raceRepository.findById(id).orElse(null);
            raceRepository.deleteById(id);
            messagingTemplate.convertAndSend("/topic/race-events", new RaceEvent("DELETE", deletedRace));
            logger.info("RaceServiceImpl - > Race deleted and STOMP event sent");
            return true;
        }
        logger.info("RaceServiceImpl - > Race not found");
        return false;
    }
}
