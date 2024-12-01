package hr.spring.race.application.command.service.model.event;

import hr.spring.race.application.command.service.model.entity.Race;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RaceEvent {

    private String action;
    private Race race;
}
