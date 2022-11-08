package ru.javandy.carshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javandy.carshop.model.Detail;
import ru.javandy.carshop.service.DetailService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DetailController {
    private List<Detail> details;

    @Autowired
    private DetailService detailService;

    @GetMapping("/details")
    public List<Detail> getAllDetails() {
        return detailService.findAll();
    }



}
