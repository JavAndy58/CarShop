package ru.javandy.carshop.service;

import ru.javandy.carshop.dto.DetailDTO;

public interface DetailService {
    DetailDTO saveDetail(DetailDTO detailDTO);
    DetailDTO findByDetailId(int id);
    DetailDTO updateDetailId(DetailDTO detailDTO, int id);
    boolean existsByDetailId(int id);
    void deleteByDetailId(int id);
}
