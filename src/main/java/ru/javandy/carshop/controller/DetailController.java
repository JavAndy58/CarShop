package ru.javandy.carshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.javandy.carshop.dto.DetailDto;
import ru.javandy.carshop.service.DetailService;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class DetailController {

    private final DetailService detailService;

    @PostMapping("/detail")
    @ResponseStatus(HttpStatus.CREATED)
    public DetailDto createDetail(@RequestBody DetailDto detailDTO) {
        return detailService.saveDetail(detailDTO);
    }

    @GetMapping("/detail/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DetailDto getDetailId(@PathVariable int id) {
        return detailService.findByDetailId(id);
    }

    @PutMapping("/detail/{id}")
    @ResponseStatus(HttpStatus.OK)
    DetailDto updateDetail(@RequestBody DetailDto detailDTO, @PathVariable int id) {
        return detailService.updateDetailId(detailDTO, id);
    }

    @DeleteMapping("/detail/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteDetail(@PathVariable int id) {
        detailService.deleteByDetailId(id);
    }

}
