package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {
    private final Map<UUID, Product> productMap;
    private final Map<UUID, Article> articleMap;

    public StorageService() {
        this.productMap = new HashMap<>();
        this.articleMap = new HashMap<>();
        initializeTestData();
    }

    private void initializeTestData() {
        // Продукты
        Product p1 = new SimpleProduct(UUID.randomUUID(), "Ноутбук", 50000);
        Product p2 = new SimpleProduct(UUID.randomUUID(), "Игровой ноутбук", 80000);
        Product p3 = new SimpleProduct(UUID.randomUUID(), "Клавиатура", 2500);
        Product p4 = new SimpleProduct(UUID.randomUUID(), "Мышь", 1500);
        Product p5 = new DiscountedProduct(UUID.randomUUID(), "Смартфон", 30000, 20);
        Product p6 = new DiscountedProduct(UUID.randomUUID(), "Наушники", 5000, 15);
        Product p7 = new FixPriceProduct(UUID.randomUUID(), "Футболка");
        Product p8 = new FixPriceProduct(UUID.randomUUID(), "Кружка");

        productMap.put(p1.getId(), p1);
        productMap.put(p2.getId(), p2);
        productMap.put(p3.getId(), p3);
        productMap.put(p4.getId(), p4);
        productMap.put(p5.getId(), p5);
        productMap.put(p6.getId(), p6);
        productMap.put(p7.getId(), p7);
        productMap.put(p8.getId(), p8);

        // Статьи
        Article a1 = new Article(UUID.randomUUID(), "Как выбрать ноутбук",
                "При выборе ноутбука важно обратить внимание на процессор");
        Article a2 = new Article(UUID.randomUUID(), "Обзор смартфонов 2024",
                "Лучшие смартфоны этого года");
        Article a3 = new Article(UUID.randomUUID(), "Ноутбук для программиста",
                "Какой ноутбук выбрать для программирования");
        Article a4 = new Article(UUID.randomUUID(), "Уход за наушниками",
                "Как продлить жизнь вашим наушникам");
        Article a5 = new Article(UUID.randomUUID(), "Игровые ноутбуки 2024 года",
                "Топ игровых ноутбуков 2024");

        articleMap.put(a1.getId(), a1);
        articleMap.put(a2.getId(), a2);
        articleMap.put(a3.getId(), a3);
        articleMap.put(a4.getId(), a4);
        articleMap.put(a5.getId(), a5);
    }

    public Collection<Product> getAllProducts() {
        return productMap.values();
    }

    public Collection<Article> getAllArticles() {
        return articleMap.values();
    }

    public Collection<Searchable> getAllSearchable() {
        List<Searchable> result = new ArrayList<>();
        result.addAll(productMap.values());
        result.addAll(articleMap.values());
        return result;
    }

    // НОВЫЙ МЕТОД
    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(productMap.get(id));
    }
}