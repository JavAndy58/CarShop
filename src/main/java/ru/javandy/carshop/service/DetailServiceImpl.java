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

    @Override
    public Optional<Detail> findById(int id) {
        return detailRepository.findById(id);
    }

    @Override
    public Detail save(Detail detail) {
        return detailRepository.save(detail);
    }

    @Override
    public List<Detail> saveAll(List<Detail> details) {
        return (List<Detail>)detailRepository.saveAll(details);
    }

    @Override
    public void deleteById(int id) {
        detailRepository.deleteById(id);
    }

    @Override
    public boolean existsById(int id) {
        return detailRepository.existsById(id);
    }
}
