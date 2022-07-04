package de.volkswagen.configuratorbackend.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class CarConfigAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String category;
    private String description;
    private String details;
    private String imgUrl;
    private String thumbnailUrl;
    private int priceInCents;
    @Enumerated
    private Type type;
    @ElementCollection(fetch = FetchType.LAZY)
    private Set<Long> exclusions = new HashSet<>();

    public CarConfigAttribute(String title, Type type) {
        this.title = title;
        this.type = type;
        this.exclusions = new HashSet<>();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public CarConfigAttribute() {
    }

    public int getPriceInCents() {
        return priceInCents;
    }

    public void setPriceInCents(int priceInCents) {
        this.priceInCents = priceInCents;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Set<Long> getExclusions() {
        return exclusions;
    }

    public void setExclusions(Set<Long> exclusions) {
        this.exclusions = exclusions;
    }
}