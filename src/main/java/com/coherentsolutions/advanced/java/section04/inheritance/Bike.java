package com.coherentsolutions.advanced.java.section04.inheritance;

import jakarta.persistence.Entity;

@Entity
public class Bike extends Vehicle {

    private boolean hasPedals;

    // Getters and setters

    public boolean isHasPedals() {
        return hasPedals;
    }

    public void setHasPedals(boolean hasPedals) {
        this.hasPedals = hasPedals;
    }
}
