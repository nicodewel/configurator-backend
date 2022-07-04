package de.volkswagen.configuratorbackend.repository;

import de.volkswagen.configuratorbackend.model.CarConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigurationRepository extends JpaRepository<CarConfig, Long> {
}