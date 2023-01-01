package ru.javandy.carshop.service;

import ru.javandy.carshop.dto.DetailDTO;
import java.util.List;

public interface DetailService {
    List<DetailDTO> getAllDetails();
    DetailDTO saveDetail(DetailDTO detailDTO);
    DetailDTO findByDetailId(int id);
    DetailDTO updateDetailId(DetailDTO detailDTO, int id);
    boolean existsByDetailId(int id);
    void deleteByDetailId(int id);
}
