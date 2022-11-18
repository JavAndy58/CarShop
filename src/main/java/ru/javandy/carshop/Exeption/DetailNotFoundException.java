package ru.javandy.carshop.Exeption;

public class DetailNotFoundException extends RuntimeException {
    public DetailNotFoundException(int id) {
        super("Could not found the detail with id " + id);
    }
}
