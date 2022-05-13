package com.example.garageSystembackend.model;

public class Main {
    public static void main(String[] args){
        Motorcycle duke125 = new Motorcycle("Duke", "4786781",65,35);
        System.out.println("the first vehicle info is"+ duke125.toString());

        Truck mercedes2000 =  new Truck("Mercedes", "4786785",80,50);
        System.out.println("the second vehicle info is"+ mercedes2000.toString());

    }
}
