package com.example.garageSystembackend;

import com.example.garageSystembackend.model.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;



@RestController
public class Controller {
    //    private static final String template = "Hello , %s!";

    private List<Vehicle> vehicleList = new ArrayList<Vehicle>(); // Vehicle collaction.

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(method = RequestMethod.GET, path = "/help") // this can show how to use all the commands.
    String helpGuide(){
        String output =
                "This is the format you can use  in the url in order to make commands: \n" +
                "1) localhost:8080/add?vehicleType={Vehicle Type} & modelName={Model Name} & powerLeft={power left}50&maxTirePressure={maxTirePressure}\n" +
                "2) localhost:8080/getVehicleByLicenseNumber ? licenseNumber={license Number}1\n" +
                "3) localhost:8080/retrieveAllAvailableVehicles\n" +
                "4) localhost:8080/inflateVehicleByLicenseNumber ? licenseNumber={license Number}\n" +
                "5) localhost:8080/addEnergyByLicenseNumber ? licenseNumber={license Number} & powerAmount={enterAmount}\n";
        return output;
    }

    enum listOfOptions{
        motorcycle,
        electricMotorcycle,
        car,
        electricCar,
        truck
    }
        @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, path = "/add") // adding vehicle to
        String addVehicle(@RequestParam String vehicleType, String modelName, int powerLeft, double maxTirePressure){
            listOfOptions theChosenOne = listOfOptions.valueOf(vehicleType);//this line helps us to know what kind of vehicle we will add.
            switch (theChosenOne) {
                case car:
                    Car car = new Car(modelName, String.valueOf(counter.incrementAndGet()), powerLeft, maxTirePressure);
                    vehicleList.add(car);
                    return "You added car: " + car.toString();
                case electricCar:
                    ElectricCar electricCar = new ElectricCar(modelName, String.valueOf(counter.incrementAndGet()), powerLeft, maxTirePressure);
                    vehicleList.add(electricCar);
                    return "You added electric car: " + electricCar.toString();
                case motorcycle:
                    Motorcycle motorcycle = new Motorcycle(modelName, String.valueOf(counter.incrementAndGet()), powerLeft, maxTirePressure);
                    vehicleList.add(motorcycle);
                    return "You added motorcycle: " + motorcycle.toString();
                case electricMotorcycle:
                    ElectricMotorcycle electricMotorcycle = new ElectricMotorcycle(modelName, String.valueOf(counter.incrementAndGet()), powerLeft, maxTirePressure);
                    vehicleList.add(electricMotorcycle);
                    return "You added electric motorcycle: " + electricMotorcycle.toString();
                case truck:
                    Truck truck = new Truck(modelName, String.valueOf(counter.incrementAndGet()), powerLeft, maxTirePressure);
                    vehicleList.add(truck);
                    return "You added a truck: " + truck.toString();
            }
        return "You must enter the type of vehicle that you want to add, try again or enter help to get help";
    }

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, path = "/getVehicleByLicenseNumber") // Adding new motorcycle from list.
    String getVehicleByLicenseNumber(@RequestParam String licenseNumber){
        for(int i = 0; i< vehicleList.size(); i++){
            if(vehicleList.get(i).getLicenseNumber().equals(licenseNumber))
                return vehicleList.get(i).toString();
        }
        return "There isnt vehicle with this number, please try again";
    }

    enum parameters{
        getModelName,
        getLicenseNumber,
        getPowerLeft,
        getMaxTirePressure
    }
    @RequestMapping(method =RequestMethod.GET, path = "/retrieveAllAvailableVehicles") // Retrieve all the available vehicles from the list.
    String retrieveAllAvailableVehicles() { //@RequestParam String[] firstParameter
        String output = "";
        if (vehicleList.size() == 0) // The case we dont have anycars.
            return "There isnt available vehicles at the moment, please try again later";
//     {#
//        if (parametersForSort.length == 1){
//            bubbleSort;
//        }
//       if(parametersForSort.length == 2){
//            parameters theFirstParameter = parameters.valueOf(parametersForSort[0]); //modelName
//            parameters theSecondParameter = parameters.valueOf(parametersForSort[1]); //powerLeft
//        }
//    }#
        Iterator<Vehicle> vehicleIterator = vehicleList.iterator();
        // Display message
        output += "Before Sorting:\n";
        // Holds true till there is single element
        // remaining in List
        while (vehicleIterator.hasNext()) {
            output += (vehicleIterator.next()+"\n");// Iterating using next() method
        }
        // Sorting using sort method of Collections class
        Collections.sort(vehicleList,
                new VehicleSortingComparator()); //#firstParameter,secondParameter

        // Display message only
        output += ("After Sorting:\n");

        // Iterating using enhanced for-loop
        // after Sorting ArrayList
        for (Vehicle vehicle : vehicleList) {
            output += vehicle.toString()+"\n";
        }
        return output;

        // Bonus: if we want to sort by one or more parameters, please enter after the endpoint, the parameters you want sort.

//        if (parametersForSort.length > 0) {// The case we have 1 or more parameters and we want to sort them.
//            int index = 0;

//            ArrayList<parameters>parametersArrayList = new ArrayList<parameters>();
//            parametersArrayList.add(theFirstParameter);
//            parametersArrayList.add(theSecondParameter);
//            while(parametersArrayList.get(index)!=null)
//            {
//                index++;
//            }
//        Comparator c = Comparator.comparing(Vehicle::); //power and then to
        // Iterating using Iterator
        // before Sorting ArrayList

    }
    @RequestMapping(method ={RequestMethod.GET, RequestMethod.POST}, path = "/inflateVehicleByLicenseNumber") //inflate the tires to max pressure by vehicle license number
        String inflateVehicleByLicenseNumber(@RequestParam String licenseNumber){
            for(int i = 0; i< vehicleList.size(); i++){
                if(vehicleList.get(i).getLicenseNumber().equals(licenseNumber))
                    vehicleList.get(i).inflate();
            }
                return "The tires of this vehicle has inflated to maximum pressure.";
    }
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, path = "/addEnergyByLicenseNumber") // add energy to a vehicle by license number
        String addEnergyByLicenseNumber(@RequestParam String licenseNumber,int powerAmount) {
            for (int i = 0; i < vehicleList.size(); i++) {
                if(powerAmount < 0)
                    return "You need to choose a positive number";
                else {
                    if (vehicleList.get(i).getLicenseNumber().equals(licenseNumber)) {
                        vehicleList.get(i).addEnergy(powerAmount);
                        return "You just filled " + powerAmount + "% of power";
                    }
                }
            }
        return "Energy has been added.";
        }
}
