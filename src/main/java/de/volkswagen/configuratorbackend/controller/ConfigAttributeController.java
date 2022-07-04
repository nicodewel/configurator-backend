package de.volkswagen.configuratorbackend.controller;

import de.volkswagen.configuratorbackend.model.CarConfigAttribute;
import de.volkswagen.configuratorbackend.model.Type;
import de.volkswagen.configuratorbackend.service.CarConfigAttributeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ConfigAttributeController {

    private final CarConfigAttributeService carConfigAttributeService;

    public ConfigAttributeController(CarConfigAttributeService carConfigAttributeService) {
        this.carConfigAttributeService = carConfigAttributeService;
    }

    @GetMapping("/api/attributes")
    public ResponseEntity<List<CarConfigAttribute>> getAttributes(){
        return ResponseEntity.ok(carConfigAttributeService.findAll());
    }

    @GetMapping("/api/attribute/type/{type}")
    public ResponseEntity<List<CarConfigAttribute>> getAttributesByType(@PathVariable Type type){
        return ResponseEntity.ok(carConfigAttributeService.findAllByType(type));
    }

    @GetMapping("/api/attribute/id/{id}")
    public ResponseEntity<CarConfigAttribute> getAttributeById(@PathVariable long id){
        return ResponseEntity.ok(carConfigAttributeService.findById(id));
    }

    @PostMapping("/api/attribute/exclusion")
    public ResponseEntity<?> saveExclusion(@RequestParam Long attrId1, @RequestParam Long attrId2){
        carConfigAttributeService.addExclusion(attrId1,attrId2);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/api/attribute")
    public ResponseEntity<?> saveAttribute(@RequestBody CarConfigAttribute carConfigAttribute){
        carConfigAttributeService.save(carConfigAttribute);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}