package com.gmail.at.irotech.generics.comparator;

import com.gmail.at.irotech.generics.model.ObjectToCompare;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Comparator;

public class GenericWeightComparator {

    public enum weightType {

        DesktopWeight("getDesktopWeight"),
        TabletWeight("getTabletWeight"),
        MobileAppWeight("getMobileWeight"),
        CloudWeight("getCloudWeight");

        private String orderMethod;

        weightType(String weightType) {
            this.orderMethod = weightType;
        }
    }

    /**
     * <p>This comparator can be used to order list of Object.</p>
     * <p>The sorted list is in descending order with null element in the end of the list.</p>
     *
     * @param methodName TabletOrderWeight, DesktopOrderWeight, UsableNetOrderWeight, MobileAppOrderWeight
     * @return ordered list by request platform
     * @throws Exception
     */
    public Comparator<ObjectToCompare> platformComparator(weightType methodName) throws Exception {
        return comparator(ObjectToCompare.class, methodName);
    }

    public <T> Comparator<T> comparator(Class<T> cls, weightType methodName) throws Exception {

        /* Find PlatformOrderWeights class inside ObjectToCompare class */
        Field objectToCompareWeightsClass = cls.getDeclaredField("objectToCompareWeights");

        /* Find one of the defined comparable fields inside PlatformOrderWeights class */
        Method method = objectToCompareWeightsClass.getType().getMethod(methodName.orderMethod);

        if (method.getParameterTypes().length != 0)
            throw new Exception("Method " + method + " takes parameters");

        Class<?> returnType = method.getReturnType();
        if (!Comparable.class.isAssignableFrom(returnType))
            throw new Exception("The return type " + returnType + " is not Comparable");

        return internalComparator(method, (Class<? extends Comparable>) returnType);
    }

    private <T, R extends Comparable<R>> Comparator<T> internalComparator(final Method method, final Class<R> returnType) throws Exception {
        return new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {

                if(o1 == o2)
                    return 0;
                if (o1 == null || ((ObjectToCompare) o1).getObjectToCompareWeights() == null)
                    return o2 ==  null || ((ObjectToCompare) o2).getObjectToCompareWeights() == null ? 0 : 1;
                if (o2 == null || ((ObjectToCompare) o2).getObjectToCompareWeights() == null)
                    return -1;
                try {
                    R a = invoke(method, o1);
                    R b = invoke(method, o2);
                    if(a == null)
                        return (b == null) ? 0 : 1;
                    if(b == null)
                        return -1;
                    return b.compareTo(a); //desc
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            /* Execute the method ClassA.getClassB.getMethod */
            private R invoke(Method method, T o) throws Exception {
                return returnType.cast(method.invoke(((ObjectToCompare) o).getObjectToCompareWeights()));
            }
        };
    }

}
