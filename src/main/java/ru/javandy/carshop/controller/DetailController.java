package ru.javandy.carshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.javandy.carshop.dto.DetailDTO;
import ru.javandy.carshop.exeption.DetailNotFoundException;
import ru.javandy.carshop.mapper.DetailMapper;
import ru.javandy.carshop.model.Detail;
import ru.javandy.carshop.service.DetailService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class DetailController {

    private final DetailService detailService;
    private final DetailMapper detailMapper;

    @GetMapping("/details")
    public List<DetailDTO> getAllDetails() {
        return detailService.getAllDetails()
                .stream()
                .map(detailMapper::toDTO)
                .collect(Collectors.toList());

    }

    @PostMapping("/detail")
    public DetailDTO createDetail(@RequestBody DetailDTO detailDTO) {
        Detail detail = detailMapper.toEntity(detailDTO);
        return detailMapper.toDTO(detailService.saveDetail(detail));
    }

    @GetMapping("/detail/{id}")
    public DetailDTO getDetailId(@PathVariable int id) {
        Detail detail = detailService.findByDetailId(id);
        return detailMapper.toDTO(detail);
    }

    @PutMapping("/detail/{id}")
    DetailDTO updateDetail(@RequestBody DetailDTO detailDTO, @PathVariable int id) {
        Detail detail = detailMapper.toEntity(detailDTO);
        return detailMapper.toDTO(detailService.updateDetailId(detail, id));
    }

    @DeleteMapping("/detail/{id}")
    String deleteDetail(@PathVariable int id) {
        if (!detailService.existsByDetailId(id)) {
            throw new DetailNotFoundException(id);
        }
        detailService.deleteByDetailId(id);
        return "Detail with id " + id + " has been delete success.";
    }

}
