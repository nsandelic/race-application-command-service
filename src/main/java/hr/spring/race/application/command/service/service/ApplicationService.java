package hr.spring.race.application.command.service.service;

import hr.spring.race.application.command.service.model.entity.Application;


import java.util.UUID;

public interface ApplicationService {

    Application createApplication(Application application);
    Application updateApplication(UUID id, Application application);
    Boolean deleteApplication(UUID id);
}
