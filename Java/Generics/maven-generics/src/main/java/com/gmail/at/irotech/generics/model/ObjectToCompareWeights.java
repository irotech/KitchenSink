package com.gmail.at.irotech.generics.model;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.Map;

public class ObjectToCompareWeights {

    protected Long objectToCompareId;

    protected ObjectToCompare objectToCompare;

    protected BigDecimal desktopWeight;
    protected BigDecimal tabletWeight;
    protected BigDecimal mobileWeight;
    protected BigDecimal cloudWeight;

    public ObjectToCompareWeights() {
    }

    public ObjectToCompareWeights(EnumMap<Platform, BigDecimal> orderWeightsByPlatform) {
        for (Map.Entry<Platform, BigDecimal> entry : orderWeightsByPlatform.entrySet()) {
            if (entry.getKey() == Platform.DESKTOP) {
                desktopWeight = entry.getValue();
            } else if (entry.getKey() == Platform.DESKTOP) {
                tabletWeight = entry.getValue();
            } else if (entry.getKey() == Platform.DESKTOP) {
                mobileWeight = entry.getValue();
            } else if (entry.getKey() == Platform.DESKTOP) {
                cloudWeight = entry.getValue();
            }
        }
    }

    public Long getObjectToCompareId() {
        return objectToCompareId;
    }

    public void setObjectToCompareId(Long objectToCompareId) {
        this.objectToCompareId = objectToCompareId;
    }

    public ObjectToCompare getObjectToCompare() {
        return objectToCompare;
    }

    public void setObjectToCompare(ObjectToCompare objectToCompare) {
        this.objectToCompare = objectToCompare;
    }

    public BigDecimal getDesktopWeight() {
        return desktopWeight;
    }

    public void setDesktopWeight(BigDecimal desktopWeight) {
        this.desktopWeight = desktopWeight;
    }

    public BigDecimal getTabletWeight() {
        return tabletWeight;
    }

    public void setTabletWeight(BigDecimal tabletWeight) {
        this.tabletWeight = tabletWeight;
    }

    public BigDecimal getMobileWeight() {
        return mobileWeight;
    }

    public void setMobileWeight(BigDecimal mobileWeight) {
        this.mobileWeight = mobileWeight;
    }

    public BigDecimal getCloudWeight() {
        return cloudWeight;
    }

    public void setCloudWeight(BigDecimal cloudWeight) {
        this.cloudWeight = cloudWeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ObjectToCompareWeights that = (ObjectToCompareWeights) o;

        if (cloudWeight != null ? !cloudWeight.equals(that.cloudWeight) : that.cloudWeight != null) return false;
        if (desktopWeight != null ? !desktopWeight.equals(that.desktopWeight) : that.desktopWeight != null)
            return false;
        if (mobileWeight != null ? !mobileWeight.equals(that.mobileWeight) : that.mobileWeight != null) return false;
        if (objectToCompare != null ? !objectToCompare.equals(that.objectToCompare) : that.objectToCompare != null)
            return false;
        if (objectToCompareId != null ? !objectToCompareId.equals(that.objectToCompareId) : that.objectToCompareId != null)
            return false;
        if (tabletWeight != null ? !tabletWeight.equals(that.tabletWeight) : that.tabletWeight != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = objectToCompareId != null ? objectToCompareId.hashCode() : 0;
        result = 31 * result + (objectToCompare != null ? objectToCompare.hashCode() : 0);
        result = 31 * result + (desktopWeight != null ? desktopWeight.hashCode() : 0);
        result = 31 * result + (tabletWeight != null ? tabletWeight.hashCode() : 0);
        result = 31 * result + (mobileWeight != null ? mobileWeight.hashCode() : 0);
        result = 31 * result + (cloudWeight != null ? cloudWeight.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ObjectToCompareWeights{" +
                "objectToCompareId=" + objectToCompareId +
                ", objectToCompare=" + objectToCompare +
                ", desktopWeight=" + desktopWeight +
                ", tabletWeight=" + tabletWeight +
                ", mobileWeight=" + mobileWeight +
                ", cloudWeight=" + cloudWeight +
                '}';
    }

}

enum Platform {

    DESKTOP("Desktop"),
    TABLET("Tablet"),
    MOBILE("Mobile"),
    CLOUD("CLoud");

    private String keyword;

    Platform(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }

    public static Platform fromKeyword(String keyword) {
        if (DESKTOP.getKeyword().equalsIgnoreCase(keyword)) {
            return DESKTOP;
        } else if (TABLET.getKeyword().equalsIgnoreCase(keyword)) {
            return TABLET;
        } else if (MOBILE.getKeyword().equalsIgnoreCase(keyword)) {
            return MOBILE;
        } else if (CLOUD.getKeyword().equalsIgnoreCase(keyword)) {
            return CLOUD;
        }
        return null;
    }

}