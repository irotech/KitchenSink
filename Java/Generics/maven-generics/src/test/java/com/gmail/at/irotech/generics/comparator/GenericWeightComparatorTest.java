package com.gmail.at.irotech.generics.comparator;

import com.gmail.at.irotech.generics.model.ObjectToCompare;
import com.gmail.at.irotech.generics.model.ObjectToCompareWeights;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class GenericWeightComparatorTest {

    private List<ObjectToCompare> ObjectToCompares;

    private ObjectToCompare ObjectToCompare1;
    private ObjectToCompare ObjectToCompare2;
    private ObjectToCompare ObjectToCompare3;
    private ObjectToCompare ObjectToCompare4;
    private ObjectToCompare ObjectToCompare5;

    private ObjectToCompareWeights objectToCompareWeights1;
    private ObjectToCompareWeights objectToCompareWeights2;
    private ObjectToCompareWeights objectToCompareWeights3;
    private ObjectToCompareWeights objectToCompareWeights4;
    private ObjectToCompareWeights objectToCompareWeights5;

    @Before
    public void setUp() {

        ObjectToCompares = new ArrayList();

        objectToCompareWeights1 = new ObjectToCompareWeights();
        objectToCompareWeights1.setTabletWeight(new BigDecimal(0));
        objectToCompareWeights1.setDesktopWeight(new BigDecimal(0));
        objectToCompareWeights1.setCloudWeight(new BigDecimal(0));
        objectToCompareWeights1.setMobileWeight(new BigDecimal(0));
        ObjectToCompare1 = buildObjectToCompare(1L, "beauty", objectToCompareWeights1);
        ObjectToCompares.add(ObjectToCompare1);

        objectToCompareWeights2 = new ObjectToCompareWeights();
        objectToCompareWeights2.setTabletWeight(new BigDecimal(0));
        objectToCompareWeights2.setDesktopWeight(new BigDecimal(0));
        objectToCompareWeights2.setCloudWeight(new BigDecimal(0));
        objectToCompareWeights2.setMobileWeight(new BigDecimal(0));
        ObjectToCompare2 = buildObjectToCompare(2L, "fashion", objectToCompareWeights2);
        ObjectToCompares.add(ObjectToCompare2);

        objectToCompareWeights3 = new ObjectToCompareWeights();
        objectToCompareWeights3.setTabletWeight(new BigDecimal(0));
        objectToCompareWeights3.setDesktopWeight(new BigDecimal(0));
        objectToCompareWeights3.setCloudWeight(new BigDecimal(0));
        objectToCompareWeights3.setMobileWeight(new BigDecimal(0));
        ObjectToCompare3 = buildObjectToCompare(3L, "sports", objectToCompareWeights3);
        ObjectToCompares.add(ObjectToCompare3);

        objectToCompareWeights4 = new ObjectToCompareWeights();
        objectToCompareWeights4.setTabletWeight(new BigDecimal(0));
        objectToCompareWeights4.setDesktopWeight(new BigDecimal(0));
        objectToCompareWeights4.setCloudWeight(new BigDecimal(0));
        objectToCompareWeights4.setMobileWeight(new BigDecimal(0));
        ObjectToCompare4 = buildObjectToCompare(4L, "fashion", objectToCompareWeights4);
        ObjectToCompares.add(ObjectToCompare4);

        objectToCompareWeights5 = new ObjectToCompareWeights();
        objectToCompareWeights5.setTabletWeight(new BigDecimal(0));
        objectToCompareWeights5.setDesktopWeight(new BigDecimal(0));
        objectToCompareWeights5.setCloudWeight(new BigDecimal(0));
        objectToCompareWeights5.setMobileWeight(new BigDecimal(0));
        ObjectToCompare5 = buildObjectToCompare(5L, "leisure", objectToCompareWeights5);
        ObjectToCompares.add(ObjectToCompare5);
    }

    @After
    public void tearDown() {
        ObjectToCompares = null;
        ObjectToCompare1 = null;
        ObjectToCompare2 = null;
        ObjectToCompare3 = null;
        ObjectToCompare4 = null;
        ObjectToCompare5 = null;
        objectToCompareWeights1 = null;
        objectToCompareWeights2 = null;
        objectToCompareWeights3 = null;
        objectToCompareWeights4 = null;
        objectToCompareWeights5 = null;
    }

    @Test
    public void testOrderByPlatformForDesktopPlatform() {

        objectToCompareWeights1.setDesktopWeight(new BigDecimal(1.5));
        objectToCompareWeights2.setDesktopWeight(new BigDecimal(1));
        objectToCompareWeights3.setDesktopWeight(new BigDecimal(1.5));
        objectToCompareWeights4.setDesktopWeight(new BigDecimal(1));
        objectToCompareWeights5.setDesktopWeight(new BigDecimal(2.5));

        assertEquals(ObjectToCompare1, ObjectToCompares.get(0));
        assertEquals(ObjectToCompare2, ObjectToCompares.get(1));
        assertEquals(ObjectToCompare3, ObjectToCompares.get(2));
        assertEquals(ObjectToCompare4, ObjectToCompares.get(3));
        assertEquals(ObjectToCompare5, ObjectToCompares.get(4));

        try {
            Comparator<ObjectToCompare> desktopComparator = new GenericWeightComparator().platformComparator(GenericWeightComparator.weightType.DesktopWeight);
            Collections.sort(ObjectToCompares, desktopComparator);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(ObjectToCompare5, ObjectToCompares.get(0));
        assertEquals(ObjectToCompare1, ObjectToCompares.get(1));
        assertEquals(ObjectToCompare3, ObjectToCompares.get(2));
        assertEquals(ObjectToCompare2, ObjectToCompares.get(3));
        assertEquals(ObjectToCompare4, ObjectToCompares.get(4));

    }

    @Test
    public void testOrderByPlatformForDesktopPlatformWithNull() {

        objectToCompareWeights1.setDesktopWeight(new BigDecimal(1.5));
        objectToCompareWeights2.setDesktopWeight(null);
        objectToCompareWeights3.setDesktopWeight(new BigDecimal(1.5));
        ObjectToCompare4.setObjectToCompareWeights(null);
        objectToCompareWeights5.setDesktopWeight(new BigDecimal(2.5));

        assertEquals(ObjectToCompare1, ObjectToCompares.get(0));
        assertNull(ObjectToCompares.get(1).getObjectToCompareWeights().getDesktopWeight()); //ObjectToCompare2
        assertEquals(ObjectToCompare3, ObjectToCompares.get(2));
        assertNull(ObjectToCompares.get(3).getObjectToCompareWeights()); //ObjectToCompare4
        assertEquals(ObjectToCompare5, ObjectToCompares.get(4));

        try {
            Comparator<ObjectToCompare> mobileComparator = new GenericWeightComparator().platformComparator(GenericWeightComparator.weightType.DesktopWeight);
            Collections.sort(ObjectToCompares, mobileComparator);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(ObjectToCompare5, ObjectToCompares.get(0));
        assertEquals(ObjectToCompare1, ObjectToCompares.get(1));
        assertEquals(ObjectToCompare3, ObjectToCompares.get(2));
        assertEquals(ObjectToCompare2, ObjectToCompares.get(3));
        assertEquals(ObjectToCompare4, ObjectToCompares.get(4));
    }

    @Test
    public void testOrderByPlatformForTabletPlatform() {

        objectToCompareWeights1.setTabletWeight(new BigDecimal(1.5));
        objectToCompareWeights2.setTabletWeight(new BigDecimal(1));
        objectToCompareWeights3.setTabletWeight(new BigDecimal(1.5));
        objectToCompareWeights4.setTabletWeight(new BigDecimal(1));
        objectToCompareWeights5.setTabletWeight(new BigDecimal(2.5));

        assertEquals(ObjectToCompare1, ObjectToCompares.get(0));
        assertEquals(ObjectToCompare2, ObjectToCompares.get(1));
        assertEquals(ObjectToCompare3, ObjectToCompares.get(2));
        assertEquals(ObjectToCompare4, ObjectToCompares.get(3));
        assertEquals(ObjectToCompare5, ObjectToCompares.get(4));

        try {
            Comparator<ObjectToCompare> tabletComparator = new GenericWeightComparator().platformComparator(GenericWeightComparator.weightType.TabletWeight);
            Collections.sort(ObjectToCompares, tabletComparator);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(ObjectToCompare5, ObjectToCompares.get(0));
        assertEquals(ObjectToCompare1, ObjectToCompares.get(1));
        assertEquals(ObjectToCompare3, ObjectToCompares.get(2));
        assertEquals(ObjectToCompare2, ObjectToCompares.get(3));
        assertEquals(ObjectToCompare4, ObjectToCompares.get(4));

    }

    @Test
    public void testOrderByPlatformForTabletPlatformWithNull() {

        objectToCompareWeights1.setTabletWeight(new BigDecimal(1.5));
        objectToCompareWeights2.setTabletWeight(null);
        objectToCompareWeights3.setTabletWeight(new BigDecimal(1.5));
        ObjectToCompare4.setObjectToCompareWeights(null);
        objectToCompareWeights5.setTabletWeight(new BigDecimal(2.5));

        assertEquals(ObjectToCompare1, ObjectToCompares.get(0));
        assertNull(ObjectToCompares.get(1).getObjectToCompareWeights().getTabletWeight()); //ObjectToCompare2
        assertEquals(ObjectToCompare3, ObjectToCompares.get(2));
        assertNull(ObjectToCompares.get(3).getObjectToCompareWeights()); //ObjectToCompare4
        assertEquals(ObjectToCompare5, ObjectToCompares.get(4));

        try {
            Comparator<ObjectToCompare> tabletComparator = new GenericWeightComparator().platformComparator(GenericWeightComparator.weightType.TabletWeight);
            Collections.sort(ObjectToCompares, tabletComparator);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(ObjectToCompare5, ObjectToCompares.get(0));
        assertEquals(ObjectToCompare1, ObjectToCompares.get(1));
        assertEquals(ObjectToCompare3, ObjectToCompares.get(2));
        assertEquals(ObjectToCompare2, ObjectToCompares.get(3));
        assertEquals(ObjectToCompare4, ObjectToCompares.get(4));
    }

    @Test
    public void testOrderByPlatformForMobilePlatform()  {

        objectToCompareWeights1.setMobileWeight(new BigDecimal(1.5));
        objectToCompareWeights2.setMobileWeight(new BigDecimal(1));
        objectToCompareWeights3.setMobileWeight(new BigDecimal(1.5));
        objectToCompareWeights4.setMobileWeight(new BigDecimal(1));
        objectToCompareWeights5.setMobileWeight(new BigDecimal(2.5));

        assertEquals(ObjectToCompare1, ObjectToCompares.get(0));
        assertEquals(ObjectToCompare2, ObjectToCompares.get(1));
        assertEquals(ObjectToCompare3, ObjectToCompares.get(2));
        assertEquals(ObjectToCompare4, ObjectToCompares.get(3));
        assertEquals(ObjectToCompare5, ObjectToCompares.get(4));

        try {
            Comparator<ObjectToCompare> mobileComparator = new GenericWeightComparator().platformComparator(GenericWeightComparator.weightType.MobileAppWeight);
            Collections.sort(ObjectToCompares, mobileComparator);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(ObjectToCompare5, ObjectToCompares.get(0));
        assertEquals(ObjectToCompare1, ObjectToCompares.get(1));
        assertEquals(ObjectToCompare3, ObjectToCompares.get(2));
        assertEquals(ObjectToCompare2, ObjectToCompares.get(3));
        assertEquals(ObjectToCompare4, ObjectToCompares.get(4));

    }

    @Test
    public void testOrderByPlatformForMobilePlatformWithNull() {

        objectToCompareWeights1.setMobileWeight(new BigDecimal(1.5));
        objectToCompareWeights2.setMobileWeight(null);
        objectToCompareWeights3.setMobileWeight(new BigDecimal(1.5));
        ObjectToCompare4.setObjectToCompareWeights(null);
        objectToCompareWeights5.setMobileWeight(new BigDecimal(2.5));

        assertEquals(ObjectToCompare1, ObjectToCompares.get(0));
        assertNull(ObjectToCompares.get(1).getObjectToCompareWeights().getMobileWeight()); //ObjectToCompare2
        assertEquals(ObjectToCompare3, ObjectToCompares.get(2));
        assertNull(ObjectToCompares.get(3).getObjectToCompareWeights()); //ObjectToCompare4
        assertEquals(ObjectToCompare5, ObjectToCompares.get(4));

        try {
            Comparator<ObjectToCompare> mobileComparator = new GenericWeightComparator().platformComparator(GenericWeightComparator.weightType.MobileAppWeight);
            Collections.sort(ObjectToCompares, mobileComparator);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(ObjectToCompare5, ObjectToCompares.get(0));
        assertEquals(ObjectToCompare1, ObjectToCompares.get(1));
        assertEquals(ObjectToCompare3, ObjectToCompares.get(2));
        assertEquals(ObjectToCompare2, ObjectToCompares.get(3));
        assertEquals(ObjectToCompare4, ObjectToCompares.get(4));
    }

    @Test
    public void testOrderByPlatformForUsablenetPlatform() {

        objectToCompareWeights1.setCloudWeight(new BigDecimal(1.5));
        objectToCompareWeights2.setCloudWeight(new BigDecimal(1));
        objectToCompareWeights3.setCloudWeight(new BigDecimal(1.5));
        objectToCompareWeights4.setCloudWeight(new BigDecimal(1));
        objectToCompareWeights5.setCloudWeight(new BigDecimal(2.5));

        assertEquals(ObjectToCompare1, ObjectToCompares.get(0));
        assertEquals(ObjectToCompare2, ObjectToCompares.get(1));
        assertEquals(ObjectToCompare3, ObjectToCompares.get(2));
        assertEquals(ObjectToCompare4, ObjectToCompares.get(3));
        assertEquals(ObjectToCompare5, ObjectToCompares.get(4));

        try {
            Comparator<ObjectToCompare> usablenetComparator = new GenericWeightComparator().platformComparator(GenericWeightComparator.weightType.CloudWeight);
            Collections.sort(ObjectToCompares, usablenetComparator);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(ObjectToCompare5, ObjectToCompares.get(0));
        assertEquals(ObjectToCompare1, ObjectToCompares.get(1));
        assertEquals(ObjectToCompare3, ObjectToCompares.get(2));
        assertEquals(ObjectToCompare2, ObjectToCompares.get(3));
        assertEquals(ObjectToCompare4, ObjectToCompares.get(4));

    }

    @Test
    public void testOrderByPlatformForUsablenetPlatformWithNull() {

        objectToCompareWeights1.setCloudWeight(new BigDecimal(1.5));
        objectToCompareWeights2.setCloudWeight(null);
        objectToCompareWeights3.setCloudWeight(new BigDecimal(1.5));
        ObjectToCompare4.setObjectToCompareWeights(null);
        objectToCompareWeights5.setCloudWeight(new BigDecimal(2.5));

        assertEquals(ObjectToCompare1, ObjectToCompares.get(0));
        assertNull(ObjectToCompares.get(1).getObjectToCompareWeights().getCloudWeight()); //ObjectToCompare2
        assertEquals(ObjectToCompare3, ObjectToCompares.get(2));
        assertNull(ObjectToCompares.get(3).getObjectToCompareWeights()); //ObjectToCompare4
        assertEquals(ObjectToCompare5, ObjectToCompares.get(4));

        try {
            Comparator<ObjectToCompare> usablenetComparator = new GenericWeightComparator().platformComparator(GenericWeightComparator.weightType.CloudWeight);
            Collections.sort(ObjectToCompares, usablenetComparator);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(ObjectToCompare5, ObjectToCompares.get(0));
        assertEquals(ObjectToCompare1, ObjectToCompares.get(1));
        assertEquals(ObjectToCompare3, ObjectToCompares.get(2));
        assertEquals(ObjectToCompare2, ObjectToCompares.get(3));
        assertEquals(ObjectToCompare4, ObjectToCompares.get(4));
    }

    private ObjectToCompare buildObjectToCompare(Long id, String desc, ObjectToCompareWeights ObjectToCompareWeights){
        ObjectToCompare objectToCompare = new ObjectToCompare();
        objectToCompare.setId(id);
        objectToCompare.setDescription(desc);
        objectToCompare.setObjectToCompareWeights(ObjectToCompareWeights);
        return objectToCompare;
    }
    
}
