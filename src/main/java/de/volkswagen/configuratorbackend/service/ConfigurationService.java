package de.volkswagen.configuratorbackend.service;

import de.volkswagen.configuratorbackend.exception.CarConfigNotFoundException;
import de.volkswagen.configuratorbackend.model.CarConfig;
import de.volkswagen.configuratorbackend.repository.ConfigurationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConfigurationService {

    private final ConfigurationRepository configurationRepository;

    public ConfigurationService(ConfigurationRepository configurationRepository) {
        this.configurationRepository = configurationRepository;
    }

    public CarConfig findById(Long id) throws CarConfigNotFoundException {
        Optional<CarConfig> config = configurationRepository.findById(id);
        if (config.isPresent()){
            return config.get();
        } else {
            throw new CarConfigNotFoundException("Id: " + id +" does not exist");
        }
    }

    public CarConfig saveConfig(CarConfig carConfig) {
        //The first 50 ids are reserved
        if (carConfig.getId() <= 50){
            carConfig.setId(0);
        }
        CarConfig config = configurationRepository.save(carConfig);
        return config;
    }

    public List<CarConfig> findAll() {
        return configurationRepository.findAll();
    }

    public List<Long> findAllIds() {
        return configurationRepository.findAll().stream().map(carConfig -> carConfig.getId()).collect(Collectors.toList());
    }
}