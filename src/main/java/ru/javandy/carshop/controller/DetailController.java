package ru.javandy.carshop.controller;

import org.springframework.web.bind.annotation.*;
import ru.javandy.carshop.dto.DetailDTO;
import ru.javandy.carshop.exeption.DetailNotFoundException;
import ru.javandy.carshop.service.DetailService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class DetailController {

    private final DetailService detailService;

    public DetailController(DetailService detailService) {
        this.detailService = detailService;
    }

    @GetMapping("/details")
    public List<DetailDTO> getAllDetails() {
        return detailService.getAllDetails();
    }

    @PostMapping("/detail")
    public DetailDTO createDetail(DetailDTO detailDTO) {
        return detailService.saveDetail(detailDTO);
    }

    @GetMapping("/car/{id}")
    public DetailDTO getDetailId(@PathVariable int id) {
        return detailService.findByDetailId(id);
    }

    @PutMapping("/car/{id}")
    DetailDTO updateDetail(@RequestBody DetailDTO detailDTO, @PathVariable int id) {
        return detailService.updateDetailId(detailDTO, id);
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
