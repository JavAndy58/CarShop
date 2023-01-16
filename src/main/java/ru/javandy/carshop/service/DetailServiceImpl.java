package ru.javandy.carshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.javandy.carshop.dto.DetailDto;
import ru.javandy.carshop.dto.OrderDto;
import ru.javandy.carshop.exeption.DetailNotFoundException;
import ru.javandy.carshop.mapper.DetailMapper;
import ru.javandy.carshop.repository.DetailRepository;

@Service
@RequiredArgsConstructor
public class DetailServiceImpl implements DetailService {
    private final DetailRepository detailRepository;
    private final DetailMapper detailMapper;
    private final OrderService orderService;

    public DetailDto saveDetail(DetailDto detailDTO) {
        return detailMapper.toDTO(detailRepository.save(detailMapper.toEntity(detailDTO)));
    }

    public DetailDto findByDetailId(int id) {
        return detailMapper.toDTO(detailRepository.findById(id).orElseThrow(() -> new DetailNotFoundException(id)));
    }

    public DetailDto updateDetailId(DetailDto newDetailDto, int id) {

        return detailMapper.toDTO(detailRepository.findById(id)
                .map(detail -> {
                    detail.setName(newDetailDto.getName());
                    detail.setAmount(newDetailDto.getAmount());
                    detail.setRetailPrice(newDetailDto.getRetailPrice());
                    detail.setSupplier(newDetailDto.getSupplier());
                    detail.setBringing(newDetailDto.isBringing());
                    return detailRepository.save(detail);
                }).orElseThrow(() -> new DetailNotFoundException(id)));
    }

    public boolean existsByDetailId(int id) {
        return detailRepository.existsById(id);
    }

    public void deleteByDetailId(int id) {
        DetailDto detailDtoDel = detailMapper.toDTO(detailRepository.findById(id).orElseThrow(() -> new DetailNotFoundException(id)));
        OrderDto orderDtoDetailDel = orderService.findByDetail(detailDtoDel);
        orderDtoDetailDel.removeDetailDto(detailDtoDel);
        orderService.saveOrder(orderDtoDetailDel);
    }
}
