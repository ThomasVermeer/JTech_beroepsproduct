package com.example.jtech_beroepsproduct.model;

/**
 * De abstracte hoofdklasse als blauwdruk voor alle entiteiten word gebruikt zodat er gebruik word gemaakt
 * van overerving
 */
public abstract class BasisModel {
    private String id;

    public BasisModel(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}