package com.example.garageSystembackend.model;

public class Motorcycle extends Vehicle {
    public Motorcycle(String modelName, String licenseNumber, int powerLeft, double maxTirePressure){
        super(modelName, licenseNumber, powerLeft, maxTirePressure);
        Wheel[] WheelsArray = new Wheel[2];
        for(int i = 0; i < WheelsArray.length; i++) {
            WheelsArray[i] = new Wheel(maxTirePressure);
        }
        this.setWheelsArray(WheelsArray);





    }
}
