package ru.javandy.carshop.service;

import ru.javandy.carshop.dto.DetailDto;

public interface DetailService {
    DetailDto saveDetail(DetailDto detailDTO);
    DetailDto findByDetailId(int id);
    DetailDto updateDetailId(DetailDto detailDTO, int id);
    boolean existsByDetailId(int id);
    void deleteByDetailId(int id);
}
