package hr.spring.race.application.command.service.controller;

import hr.spring.race.application.command.service.model.entity.Application;
import hr.spring.race.application.command.service.service.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "http://localhost:4200")
public class ApplicationController {

    private static final Logger logger = Logger.getLogger(ApplicationController.class.getName());
    private final ApplicationService applicationService;

    @Autowired
    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping
    public ResponseEntity<Application> createApplication(@RequestBody @Valid Application application) {
        logger.info("ApplicationController - > createApplication() -> application = " + application);
        Application createdApplication = applicationService.createApplication(application);
        logger.info("ApplicationController - > Returning: Application crated");
        return new ResponseEntity<>(createdApplication, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable UUID id) {
        logger.info("ApplicationController - > deleteApplication() -> Application id = " + id);
        if (applicationService.deleteApplication(id)) {
            logger.info("ApplicationController - > Returning: Application deleted");
            return ResponseEntity.noContent().build();
        } else {
            logger.info("ApplicationController - > Returning: Application not found");
            return ResponseEntity.notFound().build();
        }
    }

}
