package ru.javandy.carshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.javandy.carshop.dto.DetailDTO;
import ru.javandy.carshop.dto.OrderDTO;
import ru.javandy.carshop.exeption.DetailNotFoundException;
import ru.javandy.carshop.mapper.DetailMapper;
import ru.javandy.carshop.repository.DetailRepository;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class DetailServiceImpl implements DetailService {
    private final DetailRepository detailRepository;
    private final DetailMapper detailMapper;
    private final OrderService orderService;

    public List<DetailDTO> getAllDetails() {
        return detailRepository.findAll()
                .stream()
                .map(detailMapper::toDTO)
                .collect(Collectors.toList());
    }

    public DetailDTO saveDetail(DetailDTO detailDTO) {
        return detailMapper.toDTO(detailRepository.save(detailMapper.toEntity(detailDTO)));
    }

    public DetailDTO findByDetailId(int id) {
        return detailMapper.toDTO(detailRepository.findById(id).orElseThrow(() -> new DetailNotFoundException(id)));
    }

    public DetailDTO updateDetailId(DetailDTO newDetailDTO, int id) {

        return detailMapper.toDTO(detailRepository.findById(id)
                .map(detail -> {
                    detail.setName(newDetailDTO.getName());
                    detail.setAmount(newDetailDTO.getAmount());
                    detail.setRetailPrice(newDetailDTO.getRetailPrice());
                    detail.setSupplier(newDetailDTO.getSupplier());
                    detail.setBringing(newDetailDTO.isBringing());
                    return detailRepository.save(detail);
                }).orElseThrow(() -> new DetailNotFoundException(id)));
    }

    public boolean existsByDetailId(int id) {
        return detailRepository.existsById(id);
    }

    public void deleteByDetailId(int id) {
        DetailDTO detailDTODel = detailMapper.toDTO(detailRepository.findById(id).orElseThrow(() -> new DetailNotFoundException(id)));
        OrderDTO orderDTODetailDel = orderService.findByDetail(detailDTODel);
        orderDTODetailDel.removeDetailDTO(detailDTODel);
        orderService.saveOrder(orderDTODetailDel);
    }
}
