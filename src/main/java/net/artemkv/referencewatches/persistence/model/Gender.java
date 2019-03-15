package net.artemkv.referencewatches.persistence.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.NUMBER)
public enum Gender {
    UNISEX(0), MENS(1), LADIES(2);

    private final int value;
    Gender(int value) {
        this.value = value;
    }
}
