package hr.spring.race.application.command.service.controller;

import hr.spring.race.application.command.service.model.entity.Application;
import hr.spring.race.application.command.service.service.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    @Autowired
    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping
    public ResponseEntity<Application> createApplication(@RequestBody @Valid Application application) {
        Application createdApplication = applicationService.createApplication(application);
        return new ResponseEntity<>(createdApplication, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Application> updateApplication(@PathVariable UUID id, @RequestBody @Valid Application application) {
        Application updatedApplication = applicationService.updateApplication(id, application);
        if(updatedApplication != null)
            return new ResponseEntity<>(updatedApplication, HttpStatus.OK);
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable UUID id) {
        if (applicationService.deleteApplication(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
