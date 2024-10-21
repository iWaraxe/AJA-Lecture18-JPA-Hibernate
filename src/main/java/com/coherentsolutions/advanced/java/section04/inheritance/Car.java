package com.coherentsolutions.advanced.java.section04.inheritance;

import jakarta.persistence.Entity;

@Entity
public class Car extends Vehicle {

    private int numberOfDoors;

    // Getters and setters

    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(int numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }
}
