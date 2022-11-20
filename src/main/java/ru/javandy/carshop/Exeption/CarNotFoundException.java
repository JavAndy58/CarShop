package ru.javandy.carshop.Exeption;

public class CarNotFoundException extends RuntimeException {
    public CarNotFoundException(int id) {
        super("Could not found the car with id " + id);
    }
}
