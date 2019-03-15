package net.artemkv.referencewatches.persistence.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.NUMBER)
public enum CaseMaterial {
    STAINLESS_STEEL(0), BRONZE(1), TITANIUM(2), GOLD(3), BRASS(4);

    private final int value;
    CaseMaterial(int value) {
        this.value = value;
    }
}
