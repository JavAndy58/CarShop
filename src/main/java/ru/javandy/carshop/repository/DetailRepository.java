package ru.javandy.carshop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.javandy.carshop.model.Detail;

@Repository
public interface DetailRepository extends CrudRepository<Detail, Integer> {
}
