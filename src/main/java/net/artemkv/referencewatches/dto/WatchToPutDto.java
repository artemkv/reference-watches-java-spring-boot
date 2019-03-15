package net.artemkv.referencewatches.dto;

import net.artemkv.referencewatches.persistence.model.CaseMaterial;
import net.artemkv.referencewatches.persistence.model.Gender;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class WatchToPutDto {
    @NotNull()
    private long id;

    @NotNull()
    @Size(max = 255)
    private String model;

    @NotNull()
    @Size(max = 255)
    private String title;

    @NotNull()
    private Gender gender;

    @NotNull()
    private int caseSize;

    @NotNull()
    private CaseMaterial caseMaterial;

    @NotNull()
    private long brandId;

    @NotNull()
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

    public long getBrandId() {
        return brandId;
    }

    public void setBrand(long brandId) {
        this.brandId = brandId;
    }

    public long getMovementId() {
        return movementId;
    }

    public void setMovementId(long movementId) {
        this.movementId = movementId;
    }
}
