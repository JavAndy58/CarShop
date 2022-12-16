package ru.javandy.carshop.service;

import org.springframework.stereotype.Service;
import ru.javandy.carshop.dto.DetailDTO;
import ru.javandy.carshop.exeption.DetailNotFoundException;
import ru.javandy.carshop.model.Detail;
import ru.javandy.carshop.repository.DetailRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetailServiceImpl implements DetailService {
//    private final DetailRepository detailRepository;


//    public List<DetailDTO> getAllDetails() {
//        return detailRepository.findAll()
//            .stream()
//            .map(this::toDTO)
//            .collect(Collectors.toList());
//    }
//
//    public DetailDTO saveDetail(DetailDTO detailDTO) {
//        Detail detail = detailRepository.save(toEntity(detailDTO));
//        return toDTO(detail);
//    }
//
//    public DetailDTO findByDetailId(int id) {
//        Detail detail = detailRepository.findById(id)
//                .orElseThrow(() -> new DetailNotFoundException(id));
//        return toDTO(detail);
//    }
//
//    public DetailDTO updateDetailId(DetailDTO newDetailDTO, int id) {
//        Detail newDetail = toEntity(newDetailDTO);
//        Detail updateDetail = detailRepository.findById(id)
//                .map(detail -> {
//                    detail.setName(newDetail.getName());
//                    detail.setAmount(newDetail.getAmount());
//                    detail.setRetailPrice(newDetail.getRetailPrice());
//                    detail.setSupplier(newDetail.getSupplier());
//                    detail.setBringing(newDetail.isBringing());
//                    return detailRepository.save(detail);
//                }).orElseThrow(() -> new DetailNotFoundException(id));
//        return toDTO(updateDetail);
//    }
//
//    public boolean existsByDetailId(int id) {
//        return detailRepository.existsById(id);
//    }
//
//    public void deleteByDetailId(int id) {
//        detailRepository.deleteById(id);
//    }


}
