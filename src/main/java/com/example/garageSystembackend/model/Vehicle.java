package com.example.garageSystembackend.model;

import com.google.firebase.FirebaseOptions;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public abstract class Vehicle {

    private String modelName;

    private String licenseNumber;

    private int powerLeft; // the amount of fuel/electric which has left

    private double maxTirePressure;

    private Wheel[] WheelsArray;

    public enum parameters{
        getModelName,
        getLicenseNumber,
        getPowerLeft,
        getMaxTirePressure
    }

    public Vehicle(String modelName, String licenseNumber, int powerLeft, double maxTirePressure) { // Constructor
        this.modelName = modelName;
        this.licenseNumber = licenseNumber;
        this.powerLeft = powerLeft;
        this.maxTirePressure = maxTirePressure;
    }

    public Enum getParamaters(String parm){
        return parameters.valueOf(parm);
    }

    public void inflate(){ //For each wheel, inflate the currentTirePressure to maximum.
        for(int i = 0; i < this.WheelsArray.length; i++){
            this.WheelsArray[i].currentTirePressure = this.maxTirePressure;
        }
    }

    public void addEnergy(int power){ //adding amount of power by %
        if(this.powerLeft + power > 100) {
            this.powerLeft = 100;
        System.out.println("Its already full");
        }
        else {
            this.powerLeft += power;
            System.out.println("You just filled" + power +"% of power");
        }
    }

//    public String sortByParameters(ArrayList<Vehicle>vehicleArrayList, ArrayList<String>parameters){// Bonus: if we want to sort by one or more parameters, please enter after the endpoint, the parameters you want sort.
//        String output = "";
//

//        ArrayList<Comparator>compareArrayList = new ArrayList<Comparator>();
//        for (parameters:String parm) {
//            switch (parm){
//                case("modelName"):
//                    Comparator<Vehicle>modelName =Comparator.comparing(Vehicle::getModelName);
//                    Comparator<Vehicle>nextCompare = modelName.thenComparing(Comparator.comparing)
//                case("licenseNumber"):
//                    Comparator<Vehicle>licenseNumber =Comparator.comparing(Vehicle::getLicenseNumber);
//
//                case("powerLeft"):
//                    Comparator<Vehicle>powerLeft = Comparator.comparing(Vehicle::getPowerLeft);
//
//                case("tire"):
//                    Comparator<Vehicle>MaxTirePressure = Comparator.comparing(Vehicle::getMaxTirePressure);
//            }
//
//
//        }
//        return output;
//    }

    //getters & setters
    public String getModelName() {
        return modelName;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public int getPowerLeft() {
        return powerLeft;
    }

    public double getMaxTirePressure() {
        return maxTirePressure;
    }

    public Wheel[] getWheelsArray() {
        return WheelsArray;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public void setPowerLeft(int powerLeft) {
        this.powerLeft = powerLeft;
    }

    public void setMaxTirePressure(double maxTirePressure) {
        this.maxTirePressure = maxTirePressure;
    }

    public void setWheelsArray(Wheel[] wheelsArray) {
        WheelsArray = wheelsArray;
    }

    @Override
    public String toString() {
        String tiresDetails = "";
        for(int i = 0; i< WheelsArray.length; i++)
        {
            tiresDetails += WheelsArray[i].currentTirePressure + ",";
        }
        return "Vehicle{" +
                "modelName='" + modelName + '\'' +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", powerLeft=" + powerLeft +
                ", maxTirePressure=" + maxTirePressure +
                ", WheelsArray=" + tiresDetails+
                '}';
    }
}
