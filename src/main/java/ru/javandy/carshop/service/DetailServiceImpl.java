package ru.javandy.carshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.javandy.carshop.exeption.DetailNotFoundException;
import ru.javandy.carshop.model.Detail;
import ru.javandy.carshop.repository.DetailRepository;
import java.util.List;


@Service
@RequiredArgsConstructor
public class DetailServiceImpl implements DetailService {
    private final DetailRepository detailRepository;

    public List<Detail> getAllDetails() {
        return detailRepository.findAll();
    }

    public Detail saveDetail(Detail detail) {
        return detailRepository.save(detail);
    }

    public Detail findByDetailId(int id) {
        return detailRepository.findById(id).orElseThrow(() -> new DetailNotFoundException(id));
    }

    public Detail updateDetailId(Detail newDetail, int id) {

        return detailRepository.findById(id)
                .map(detail -> {
                    detail.setName(newDetail.getName());
                    detail.setAmount(newDetail.getAmount());
                    detail.setRetailPrice(newDetail.getRetailPrice());
                    detail.setSupplier(newDetail.getSupplier());
                    detail.setBringing(newDetail.isBringing());
                    return detailRepository.save(detail);
                }).orElseThrow(() -> new DetailNotFoundException(id));

    }

    public boolean existsByDetailId(int id) {
        return detailRepository.existsById(id);
    }

    public void deleteByDetailId(int id) {
        detailRepository.deleteById(id);
    }
}
