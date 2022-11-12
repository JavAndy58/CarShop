package ru.javandy.carshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.javandy.carshop.model.Detail;
import ru.javandy.carshop.service.DetailService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class DetailController {

    @Autowired
    private DetailService detailService;

    @GetMapping("/details")
    public List<Detail> getAllDetails() {
        return detailService.findAll();
    }

    @PostMapping("/details")
    public Detail createDetail(@RequestBody Detail detail) {
        return detailService.save(detail);
    }
}
