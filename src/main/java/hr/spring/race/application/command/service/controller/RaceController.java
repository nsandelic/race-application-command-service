package hr.spring.race.application.command.service.controller;

import hr.spring.race.application.command.service.model.entity.Race;
import hr.spring.race.application.command.service.service.RaceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/races")
@CrossOrigin(origins = "*")
public class RaceController {

    private final RaceService raceService;

    @Autowired
    public RaceController(RaceService raceService) {
        this.raceService = raceService;
    }

    @PostMapping
    public ResponseEntity<Race> createRace(@RequestBody @Valid Race race) {
        Race createdRace = raceService.createRace(race);
        return new ResponseEntity<>(createdRace, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Race> updateRace(@PathVariable UUID id, @RequestBody @Valid Race race) {
        Race updatedRace = raceService.updateRace(id, race);
        if(updatedRace != null)
            return new ResponseEntity<>(updatedRace, HttpStatus.OK);
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRace(@PathVariable UUID id) {
        if (raceService.deleteRace(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
