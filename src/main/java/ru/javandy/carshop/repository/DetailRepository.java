package ru.javandy.carshop.repository;

import org.springframework.data.repository.CrudRepository;
import ru.javandy.carshop.model.Detail;

public interface DetailRepository extends CrudRepository<Detail, Integer> {
}
