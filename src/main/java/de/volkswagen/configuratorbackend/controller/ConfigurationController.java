package de.volkswagen.configuratorbackend.controller;

import de.volkswagen.configuratorbackend.exception.CarConfigNotFoundException;
import de.volkswagen.configuratorbackend.model.CarConfig;
import de.volkswagen.configuratorbackend.service.ConfigurationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ConfigurationController {

    private final ConfigurationService configurationService;

    public ConfigurationController(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    @GetMapping("/api/configuration/{id}")
    public ResponseEntity<CarConfig> getConfigurationById(@PathVariable Long id) throws CarConfigNotFoundException {
        return ResponseEntity.ok().body(configurationService.findById(id));
    }

    @GetMapping("/api/configurations")
    public ResponseEntity<List<CarConfig>> getAllConfigurations() {
        return ResponseEntity.ok().body(configurationService.findAll());
    }

    @GetMapping("/api/configurations/id")
    public ResponseEntity<List<Long>> getAllConfigurationsId() {
        return ResponseEntity.ok().body(configurationService.findAllIds());
    }

    @PostMapping("/api/configuration")
    public ResponseEntity<CarConfig> postConfiguration(@RequestBody CarConfig carConfig) {
        return ResponseEntity.status(HttpStatus.CREATED).body(configurationService.saveConfig(carConfig));
    }
}