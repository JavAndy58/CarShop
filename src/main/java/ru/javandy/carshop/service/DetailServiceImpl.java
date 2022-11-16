package ru.javandy.carshop.service;

import org.springframework.stereotype.Service;
import ru.javandy.carshop.model.Detail;
import ru.javandy.carshop.repository.DetailRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DetailServiceImpl implements DetailService {
    private final DetailRepository detailRepository;

    public DetailServiceImpl(DetailRepository detailRepository) {
        this.detailRepository = detailRepository;
    }

    @Override
    public List<Detail> findAll() {
        List<Detail> rsl = new ArrayList<>();
        detailRepository.findAll().forEach(rsl::add);
        return rsl;
    }

    public Optional<Detail> findById(int id) {
        return detailRepository.findById(id);
    }

    @Override
    public Detail save(Detail detail) {
        return detailRepository.save(detail);
    }

    public void delete(Detail detail) {
        detailRepository.delete(detail);
    }
}
