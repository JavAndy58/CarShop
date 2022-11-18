package ru.javandy.carshop.service;

import ru.javandy.carshop.model.Detail;

import java.util.List;
import java.util.Optional;

public interface DetailService {
    List<Detail> findAll();
    Detail save(Detail detail);
    Optional<Detail> findById(int id);
    boolean existsById(int id);
    void deleteById(int id);
}
