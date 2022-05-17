package com.example.garageSystembackend.model;

import java.util.Comparator;

public class VehicleSortingComparator implements Comparator<Vehicle> {
//
//    {#
//    private String firstParameter;
//    private String secondParameter;
//    public VehicleSortingComparator(String firstParameter,String secondParameter){
//        this.firstParameter = firstParameter;
//        this.secondParameter = secondParameter;
//    }
//    }#
//    enum parameters{
//        getModelName,
//        getLicenseNumber,
//        getPowerLeft,
//        getMaxTirePressure
//    }

    // Method 1
    // To compare vehicles
    public int compare(Vehicle vehicle1,
                       Vehicle vehicle2) //parameters firstParameter, parameters secondParameter
    {
        int comparator1 = vehicle1.getModelName().compareTo(
                vehicle2.getModelName());
        int comparator2 = vehicle1.getLicenseNumber().compareTo(
                vehicle2.getLicenseNumber());
//        {#
//        int comparator1 = vehicle1.getParameter(firstParameter.name()).compareTo(
//                vehicle2.getParameters(firstParameter.name())
//        );
//
//        int comparator2 = vehicle1.getParameters(secondParameter.name()).compareTo(
//                vehicle2.getParameters(secondParameter.name())
//        );
//        }#
        return (comparator1 == 0)   ? comparator2
                                    : comparator1;
    }


}
