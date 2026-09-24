package org.skypro.skyshop.model.basket;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@SessionScope
public class ProductBasket {
    private final Map<UUID, Integer> products;

    public ProductBasket() {
        this.products = new HashMap<>();
    }

    // Метод добавления продукта в корзину
    public void addProduct(UUID id) {
        products.computeIfAbsent(id, k -> 0);
        products.computeIfPresent(id, (k, v) -> v + 1);
    }

    // Метод получения всех продуктов в корзине
    public Map<UUID, Integer> getProducts() {
        return Collections.unmodifiableMap(products);
    }
}