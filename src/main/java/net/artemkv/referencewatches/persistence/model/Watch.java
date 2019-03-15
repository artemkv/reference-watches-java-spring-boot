package net.artemkv.referencewatches.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.ZonedDateTime;

@Entity
public class Watch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 255, nullable = false)
    private String model;

    @Column(length = 255, nullable = false)
    private String title;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = false)
    private int caseSize;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private CaseMaterial caseMaterial;

    @Column(nullable = false)
    private ZonedDateTime dateCreated = ZonedDateTime.now();

    @ManyToOne
    @JoinColumn(name = "brand_id", insertable = false, updatable = false)
    private Brand brand;

    @Column(name = "brand_id", nullable = false)
    private long brandId;

    @ManyToOne
    @JoinColumn(name = "movement_id", insertable = false, updatable = false)
    private Movement movement;

    @Column(name = "movement_id", nullable = false)
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

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Movement getMovement() {
        return movement;
    }

    public void setMovement(Movement movement) {
        this.movement = movement;
    }

    public long getBrandId() {
        return brandId;
    }

    public void setBrandId(long brandId) {
        this.brandId = brandId;
    }

    public long getMovementId() {
        return movementId;
    }

    public void setMovementId(long movementId) {
        this.movementId = movementId;
    }
}
