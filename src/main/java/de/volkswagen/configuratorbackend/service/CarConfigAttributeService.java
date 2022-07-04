package de.volkswagen.configuratorbackend.service;

import de.volkswagen.configuratorbackend.exception.CarConfigAttributeNotFoundException;
import de.volkswagen.configuratorbackend.model.CarConfigAttribute;
import de.volkswagen.configuratorbackend.model.Type;
import de.volkswagen.configuratorbackend.repository.CarConfigAttributeRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Optional;

@Service
public class CarConfigAttributeService {

    private final CarConfigAttributeRepository carConfigAttributeRepository;

    public CarConfigAttributeService(CarConfigAttributeRepository carConfigAttributeRepository) {
        this.carConfigAttributeRepository = carConfigAttributeRepository;
    }

    public void addExclusion(long exclusionId1, long exclusionId2){
        Optional<CarConfigAttribute> attr1 = carConfigAttributeRepository.findById(exclusionId1);
        Optional<CarConfigAttribute> attr2 = carConfigAttributeRepository.findById(exclusionId2);
        if (!attr1.isPresent()) {
            throw new CarConfigAttributeNotFoundException("Id: " + exclusionId1 + "not found.");
        } else if (!attr2.isPresent()){
            throw new CarConfigAttributeNotFoundException("Id: " + exclusionId2 + "not found.");
        }
       attr1.get().getExclusions().add(exclusionId2);
       attr2.get().getExclusions().add(exclusionId1);
       carConfigAttributeRepository.save(attr1.get());
       carConfigAttributeRepository.save(attr2.get());
    }

    public CarConfigAttribute findById(long id){
        Optional<CarConfigAttribute> carConfigAttribute = carConfigAttributeRepository.findById(id);
        if (carConfigAttribute.isPresent()){
            return carConfigAttribute.get();
        } else {
            throw new CarConfigAttributeNotFoundException("Id: " + id + " does not exist");
        }
    }

    public List<CarConfigAttribute> findAll(){
        return carConfigAttributeRepository.findAll();
    }

    public List<CarConfigAttribute> findAllByType(Type type){
        return carConfigAttributeRepository.findAllByType(type);
    }

    public void save(CarConfigAttribute carConfigAttribute){
        carConfigAttributeRepository.save(carConfigAttribute);
    }
}