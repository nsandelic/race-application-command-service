package hr.spring.race.application.command.service.service;

import hr.spring.race.application.command.service.Repository.ApplicationRepository;
import hr.spring.race.application.command.service.model.entity.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private ApplicationRepository applicationRepository;

    @Autowired
    public ApplicationServiceImpl(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Override
    public Application createApplication(Application application) {
        return applicationRepository.save(application);
    }

    @Override
    public Application updateApplication(UUID id, Application application) {
        if (applicationRepository.existsById(id)) {
            application.setId(id);
            return applicationRepository.save(application);
        }
        return null;
    }

    @Override
    public Boolean deleteApplication(UUID id) {
        if (applicationRepository.existsById(id)) {
            applicationRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
