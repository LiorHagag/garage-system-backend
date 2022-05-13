package com.example.garageSystembackend.model;

public abstract class Vehicle {

    private String modelName;

    private String licenseNumber;

    private int powerLeft; // the amount of fuel/electric which has left

    private double maxTirePressure;

    private Wheel[] WheelsArray;

    public Vehicle(String modelName, String licenseNumber, int powerLeft, double maxTirePressure) { // Constructor
        System.out.println("Constructing an Employee");
        this.modelName = modelName;
        this.licenseNumber = licenseNumber;
        this.powerLeft = powerLeft;
        this.maxTirePressure = maxTirePressure;


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
