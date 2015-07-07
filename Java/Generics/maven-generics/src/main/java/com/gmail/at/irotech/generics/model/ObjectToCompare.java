package com.gmail.at.irotech.generics.model;

public class ObjectToCompare {

    private Long id;

    private String description;

    private ObjectToCompareWeights objectToCompareWeights;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ObjectToCompareWeights getObjectToCompareWeights() {
        return objectToCompareWeights;
    }

    public void setObjectToCompareWeights(ObjectToCompareWeights objectToCompareWeights) {
        this.objectToCompareWeights = objectToCompareWeights;
    }

}
