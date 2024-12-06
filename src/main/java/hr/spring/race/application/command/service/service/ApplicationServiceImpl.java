package hr.spring.race.application.command.service.service;

import hr.spring.race.application.command.service.model.event.ApplicationEvent;
import hr.spring.race.application.command.service.repository.ApplicationRepository;
import hr.spring.race.application.command.service.model.entity.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.logging.Logger;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private static final Logger logger = Logger.getLogger(ApplicationServiceImpl.class.getName());
    private ApplicationRepository applicationRepository;
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public ApplicationServiceImpl(ApplicationRepository applicationRepository, SimpMessagingTemplate messagingTemplate) {
        this.applicationRepository = applicationRepository;
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public Application createApplication(Application application) {
        logger.info("ApplicationServiceImpl - > createApplication()");
        Application createdApplication = applicationRepository.save(application);
        messagingTemplate.convertAndSend("/topic/application-events", new ApplicationEvent("CREATE", createdApplication));
        logger.info("ApplicationServiceImpl - > Application created and STOMP event sent");
        return createdApplication;

    }

    @Override
    public Boolean deleteApplication(UUID id) {
        logger.info("ApplicationServiceImpl - > deleteApplication()");
        if (applicationRepository.existsById(id)) {
            Application deletedApplication = applicationRepository.findById(id).orElse(null);
            applicationRepository.deleteById(id);
            messagingTemplate.convertAndSend("/topic/application-events", new ApplicationEvent("DELETE", deletedApplication));
            logger.info("ApplicationServiceImpl - > Application deleted and STOMP event sent");
            return true;
        }
        logger.info("ApplicationServiceImpl - > Application not found");
        return false;
    }
}
