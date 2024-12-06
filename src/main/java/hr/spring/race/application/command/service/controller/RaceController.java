package hr.spring.race.application.command.service.controller;

import hr.spring.race.application.command.service.model.entity.Race;
import hr.spring.race.application.command.service.service.RaceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/races")
@CrossOrigin(origins = "http://localhost:4200")
public class RaceController {

    private static final Logger logger = Logger.getLogger(RaceController.class.getName());
    private final RaceService raceService;

    @Autowired
    public RaceController(RaceService raceService) {
        this.raceService = raceService;
    }

    @PostMapping
    public ResponseEntity<Race> createRace(@RequestBody @Valid Race race) {
        logger.info("RaceController - > createRace()");
        Race createdRace = raceService.createRace(race);
        logger.info("RaceController - > Returning: Race crated");
        return new ResponseEntity<>(createdRace, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Race> updateRace(@PathVariable UUID id, @RequestBody @Valid Race race) {
        logger.info("RaceController - > getAllRaces()");
        Race updatedRace = raceService.updateRace(id, race);
        if(updatedRace != null) {
            logger.info("RaceController - > Returning: Race updated");
            return new ResponseEntity<>(updatedRace, HttpStatus.OK);
        }
        else {
            logger.info("RaceController - > Returning: Race not found");
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRace(@PathVariable UUID id) {
        logger.info("RaceController - > getAllRaces()");
        if (raceService.deleteRace(id)) {
            logger.info("RaceController - > Returning: Race deleted");
            return ResponseEntity.noContent().build();
        } else {
            logger.info("RaceController - > Returning: Race not found");
            return ResponseEntity.notFound().build();
        }
    }

}
