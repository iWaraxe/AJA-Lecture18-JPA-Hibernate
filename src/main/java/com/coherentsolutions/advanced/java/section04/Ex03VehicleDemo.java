package com.coherentsolutions.advanced.java.section04.inheritance;

import jakarta.persistence.*;

import java.util.List;

public class Ex03VehicleDemo {

    public static void main(String[] args) {
        // Create EntityManagerFactory and EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager em = emf.createEntityManager();

        // Begin transaction
        em.getTransaction().begin();

        // Create and persist a Car
        Car car = new Car();
        car.setBrand("Toyota");
        car.setNumberOfDoors(4);
        em.persist(car);

        // Create and persist a Bike
        Bike bike = new Bike();
        bike.setBrand("Trek");
        bike.setHasPedals(true);
        em.persist(bike);

        // Commit the transaction
        em.getTransaction().commit();

        // Query all vehicles (Car and Bike) from the database
        TypedQuery<Vehicle> query = em.createQuery("SELECT v FROM Vehicle v", Vehicle.class);
        List<Vehicle> vehicles = query.getResultList();

        // Display all vehicles
        for (Vehicle vehicle : vehicles) {
            System.out.println("Vehicle ID: " + vehicle.getId() + ", Brand: " + vehicle.getBrand());
            if (vehicle instanceof Car) {
                System.out.println("This is a Car with " + ((Car) vehicle).getNumberOfDoors() + " doors.");
            } else if (vehicle instanceof Bike) {
                System.out.println("This is a Bike. Has pedals: " + ((Bike) vehicle).isHasPedals());
            }
        }

        // Close EntityManager and EntityManagerFactory
        em.close();
        emf.close();
    }
}
