package net.artemkv.referencewatches.dto;

import net.artemkv.referencewatches.persistence.model.CaseMaterial;
import net.artemkv.referencewatches.persistence.model.Gender;

import java.time.ZonedDateTime;

public class WatchDto {
    private long id;
    private String model;
    private String title;
    private Gender gender;
    private int caseSize;
    private CaseMaterial caseMaterial;
    private ZonedDateTime dateCreated = ZonedDateTime.now();
    private BrandDto brand;
    private long movementId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getCaseSize() {
        return caseSize;
    }

    public void setCaseSize(int caseSize) {
        this.caseSize = caseSize;
    }

    public CaseMaterial getCaseMaterial() {
        return caseMaterial;
    }

    public void setCaseMaterial(CaseMaterial caseMaterial) {
        this.caseMaterial = caseMaterial;
    }

    public ZonedDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(ZonedDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public BrandDto getBrand() {
        return brand;
    }

    public void setBrand(BrandDto brand) {
        this.brand = brand;
    }

    public long getMovementId() {
        return movementId;
    }

    public void setMovementId(long movementId) {
        this.movementId = movementId;
    }
}
