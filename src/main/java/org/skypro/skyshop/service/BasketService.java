package org.skypro.skyshop.service;

import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BasketService {
    private final ProductBasket productBasket;
    private final StorageService storageService;

    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    // Метод добавления товара в корзину по id
    public void addProduct(UUID id) {
        // Проверяем, существует ли продукт
        storageService.getProductById(id)
                .orElseThrow(() -> new IllegalArgumentException("Продукт с id " + id + " не найден"));

        // Добавляем в корзину
        productBasket.addProduct(id);
    }

    // Метод получения корзины пользователя
    public UserBasket getUserBasket() {
        Map<UUID, Integer> basketMap = productBasket.getProducts();

        List<BasketItem> items = basketMap.entrySet().stream()
                .map(entry -> {
                    UUID productId = entry.getKey();
                    int quantity = entry.getValue();
                    Product product = storageService.getProductById(productId)
                            .orElseThrow(() -> new IllegalArgumentException("Продукт с id " + productId + " не найден"));
                    return new BasketItem(product, quantity);
                })
                .collect(Collectors.toList());

        return new UserBasket(items);
    }
}