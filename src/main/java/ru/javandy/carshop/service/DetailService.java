package ru.javandy.carshop.service;

import ru.javandy.carshop.model.Detail;
import java.util.List;

public interface DetailService {
    List<Detail> getAllDetails();
    Detail saveDetail(Detail detail);
    Detail findByDetailId(int id);
    Detail updateDetailId(Detail detail, int id);
    boolean existsByDetailId(int id);
    void deleteByDetailId(int id);
}
