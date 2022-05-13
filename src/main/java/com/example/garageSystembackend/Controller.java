package com.example.garageSystembackend;

import com.example.garageSystembackend.model.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class Controller {
    //    private static final String template = "Hello , %s!";

    private List<Vehicle> vehicleList = new ArrayList<Vehicle>(); // Vehicle collaction.

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, path = "/addMotorcycle") // adding new motorcycle to my arrayList.
    String addMotorcycle(@RequestParam String modelName, int powerLeft, double maxTirePressure){
        Motorcycle motorcycle = new Motorcycle(modelName, String.valueOf(counter.incrementAndGet()),powerLeft, maxTirePressure);
        vehicleList.add(motorcycle);
        return "You added a vehicle: " + motorcycle.toString();
    }

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, path = "/addElectricMotorcycle") // adding new electric motorcycle to my arrayList.
    String addElectircMotorcycle(@RequestParam String modelName, int powerLeft, double maxTirePressure){
        ElectricMotorcycle electricMotorcycle = new ElectricMotorcycle(modelName, String.valueOf(counter.incrementAndGet()),powerLeft, maxTirePressure);
        vehicleList.add(electricMotorcycle);
        return "You added a vehicle: " + electricMotorcycle.toString();
    }

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, path = "/addCar") // adding new car to my arrayList.
    String addCar(@RequestParam String modelName, int powerLeft, double maxTirePressure){
        Car car = new Car(modelName, String.valueOf(counter.incrementAndGet()),powerLeft, maxTirePressure);
        vehicleList.add(car);
        return "You added a vehicle: " + car.toString();
    }

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, path = "/addElectricCar") // adding new electric car to my arrayList.
    String addElectricCar(@RequestParam String modelName, int powerLeft, double maxTirePressure){
        ElectricCar electricCar = new ElectricCar(modelName, String.valueOf(counter.incrementAndGet()),powerLeft, maxTirePressure);
        vehicleList.add(electricCar);
        return "You added a vehicle: " + electricCar.toString();
    }

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, path = "/addTruck") // adding new truck to my arrayList.
    String addTruck(@RequestParam String modelName, int powerLeft, double maxTirePressure){
        Truck truck = new Truck(modelName, String.valueOf(counter.incrementAndGet()),powerLeft, maxTirePressure);
        vehicleList.add(truck);
        return "You added a vehicle: " + truck.toString();
    }



    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, path = "/getVehicleByLicenseNumber") // Adding new motorcycle from list.
    String getVehicleByLicenseNumber(@RequestParam String licenseNumber){
        for(int i = 0; i< vehicleList.size(); i++){
            if(vehicleList.get(i).getLicenseNumber().equals(licenseNumber))
                return vehicleList.get(i).toString();
        }
        return "There isnt vehicle with this number, please try again";
    }

    @RequestMapping(method =RequestMethod.GET, path = "/retrieveAllAvailableVehicles") // Retrieve all the available vehicles from the list.
    String retrieveAllAvailableVehicles(){
        String output = "";
        for(int i = 0; i< vehicleList.size(); i++){
            output += vehicleList.get(i).toString() +"\n";
        }
        if(vehicleList.size()>0)
            return output;
        else
            return "There isnt available vehicles at the moment, please try again later";
    }

    @RequestMapping(method ={RequestMethod.GET, RequestMethod.POST}, path = "/inflateVehicleByLicenseNumber") //inflate the tires to max pressure by vehicle license number
        String inflateVehicleByLicenseNumber(@RequestParam String licenseNumber){
            for(int i = 0; i< vehicleList.size(); i++){
                if(vehicleList.get(i).getLicenseNumber().equals(licenseNumber)){
                    vehicleList.get(i).inflate();
                }
            }
                return "The tires of the vehicle has inflated to maximum pressure.";

    }

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, path = "/addEnergyByLicenseNumber") // add energy to a vehicle by license number
        String addEnergyByLicenseNumber(@RequestParam String licenseNumber,int powerAmount) {
            for (int i = 0; i < vehicleList.size(); i++) {
                if (vehicleList.get(i).getLicenseNumber().equals(licenseNumber)) {
                    vehicleList.get(i).addEnergy(powerAmount);
                    if (vehicleList.get(i).getPowerLeft() + powerAmount > 100) {
                        return "Its already full";
                    } else
                        return "You just filled " + powerAmount + "% of power";
                }
            }
        return "Energy has been added.";
        }

}
