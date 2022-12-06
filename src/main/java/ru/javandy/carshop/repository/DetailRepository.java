package ru.javandy.carshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.javandy.carshop.model.Detail;

@Repository
public interface DetailRepository extends JpaRepository<Detail, Integer> {
}
