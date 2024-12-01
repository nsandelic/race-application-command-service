package hr.spring.race.application.command.service.model.event;

import hr.spring.race.application.command.service.model.entity.Application;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationEvent {

    private String action;
    private Application application;
}
