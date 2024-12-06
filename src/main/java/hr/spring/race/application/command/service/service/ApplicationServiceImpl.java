package hr.spring.race.application.command.service.service;

import hr.spring.race.application.command.service.model.event.ApplicationEvent;
import hr.spring.race.application.command.service.repository.ApplicationRepository;
import hr.spring.race.application.command.service.model.entity.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private ApplicationRepository applicationRepository;
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public ApplicationServiceImpl(ApplicationRepository applicationRepository, SimpMessagingTemplate messagingTemplate) {
        this.applicationRepository = applicationRepository;
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public Application createApplication(Application application) {
        Application createdApplication = applicationRepository.save(application);
        messagingTemplate.convertAndSend("/topic/application-events", new ApplicationEvent("CREATE", createdApplication));
        return createdApplication;

    }

    @Override
    public Boolean deleteApplication(UUID id) {
        if (applicationRepository.existsById(id)) {
            Application deletedApplication = applicationRepository.findById(id).orElse(null);
            applicationRepository.deleteById(id);
            messagingTemplate.convertAndSend("/topic/application-events", new ApplicationEvent("DELETE", deletedApplication));
            return true;
        }
        return false;
    }
}
