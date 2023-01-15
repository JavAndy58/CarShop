package ru.javandy.carshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/detail")
    @ResponseStatus(HttpStatus.CREATED)
    public DetailDTO createDetail(@RequestBody DetailDTO detailDTO) {
        return detailService.saveDetail(detailDTO);
    }

    @GetMapping("/detail/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DetailDTO getDetailId(@PathVariable int id) {
        return detailService.findByDetailId(id);
    }

    @PutMapping("/detail/{id}")
    @ResponseStatus(HttpStatus.OK)
    DetailDTO updateDetail(@RequestBody DetailDTO detailDTO, @PathVariable int id) {
        return detailService.updateDetailId(detailDTO, id);
    }

    @DeleteMapping("/detail/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteDetail(@PathVariable int id) {
        detailService.deleteByDetailId(id);
    }

}
