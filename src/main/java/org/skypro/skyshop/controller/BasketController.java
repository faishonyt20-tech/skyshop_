package org.skypro.skyshop.controller;

import org.skypro.skyshop.service.BasketService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class BasketController {
    private final BasketService basketService;

    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @GetMapping("/basket/add")
    public void addProduct(@RequestParam UUID id) {
        basketService.addProduct(id);
    }
}