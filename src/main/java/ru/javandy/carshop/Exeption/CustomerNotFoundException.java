package ru.javandy.carshop.Exeption;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(int id) {
        super("Could not found the customer with id " + id);
    }
}
