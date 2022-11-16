package ru.javandy.carshop.service;

import ru.javandy.carshop.model.Detail;

import java.util.List;

public interface DetailService {
    List<Detail> findAll();
    Detail save(Detail detail);
}
