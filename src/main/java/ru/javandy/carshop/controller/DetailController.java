package ru.javandy.carshop.controller;

import org.springframework.web.bind.annotation.*;
import ru.javandy.carshop.Exeption.DetailNotFoundException;
import ru.javandy.carshop.model.Detail;
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
    public List<Detail> getAllDetails() {
        return detailService.findAll();
    }

    @PostMapping("/detail")
    public Detail createDetail(@RequestBody Detail detail) {
        return detailService.save(detail);
    }

    @GetMapping("/detail/{id}")
    Detail getDetailById(@PathVariable int id) {
        return detailService.findById(id)
                .orElseThrow(() -> new DetailNotFoundException(id));
    }

    @PutMapping("/detail/{id}")
    Detail updateDetail(@RequestBody Detail newDetail, @PathVariable int id) {
        return detailService.findById(id)
                .map(detail -> {
                    detail.setName(newDetail.getName());
                    detail.setPurchasePrice(newDetail.getPurchasePrice());
                    detail.setRetailPrice(newDetail.getRetailPrice());
                    return detailService.save(detail);
                }).orElseThrow(() -> new DetailNotFoundException(id));
    }

    @DeleteMapping("/detail/{id}")
    String deleteDetail(@PathVariable int id) {
        if (!detailService.existsById(id)) {
            throw new DetailNotFoundException(id);
        }
        detailService.deleteById(id);
        return "Detail with id " + id + " has been deleted success.";
    }
}
