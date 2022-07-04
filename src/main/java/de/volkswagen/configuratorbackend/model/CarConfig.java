package de.volkswagen.configuratorbackend.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class CarConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.REMOVE })
    @JoinTable(
            name = "configuration_attributes",
            joinColumns = @JoinColumn(name = "configuration_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
    private Set<CarConfigAttribute> carConfigAttributes;

    public CarConfig() {
        this.carConfigAttributes = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<CarConfigAttribute> getCarConfigAttributes() {
        return carConfigAttributes;
    }

    public void setCarConfigAttributes(Set<CarConfigAttribute> carConfigAttributes) {
        this.carConfigAttributes = carConfigAttributes;
    }

    public void addCarConfigAttribute(CarConfigAttribute carConfigAttribute){
        this.getCarConfigAttributes().add(carConfigAttribute);
    }
}