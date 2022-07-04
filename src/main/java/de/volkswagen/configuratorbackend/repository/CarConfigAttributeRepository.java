package de.volkswagen.configuratorbackend.repository;

import de.volkswagen.configuratorbackend.model.CarConfigAttribute;
import de.volkswagen.configuratorbackend.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CarConfigAttributeRepository extends JpaRepository<CarConfigAttribute,Long> {
    List<CarConfigAttribute> findAllByType(Type type);
}