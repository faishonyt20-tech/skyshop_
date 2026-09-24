package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.*;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {
    private final Map<UUID, Product> products;
    private final Map<UUID, Article> articles;

    public StorageService() {
        this.products = new HashMap<>();
        this.articles = new HashMap<>();
        initStorage();
    }

    private void initStorage() {
        Product product1 = new SimpleProduct(UUID.randomUUID(), "Ноутбук", 50000);
        Product product2 = new SimpleProduct(UUID.randomUUID(), "Игровой ноутбук", 80000);
        Product product3 = new SimpleProduct(UUID.randomUUID(), "Клавиатура", 2500);
        Product product4 = new SimpleProduct(UUID.randomUUID(), "Мышь", 1500);
        Product product5 = new DiscountedProduct(UUID.randomUUID(), "Смартфон", 30000, 20);
        Product product6 = new DiscountedProduct(UUID.randomUUID(), "Наушники", 5000, 15);
        Product product7 = new FixPriceProduct(UUID.randomUUID(), "Футболка");
        Product product8 = new FixPriceProduct(UUID.randomUUID(), "Кружка");

        products.put(product1.getId(), product1);
        products.put(product2.getId(), product2);
        products.put(product3.getId(), product3);
        products.put(product4.getId(), product4);
        products.put(product5.getId(), product5);
        products.put(product6.getId(), product6);
        products.put(product7.getId(), product7);
        products.put(product8.getId(), product8);

        Article article1 = new Article(UUID.randomUUID(),
                "Как выбрать ноутбук",
                "При выборе ноутбука важно обратить внимание на процессор и оперативную память");
        Article article2 = new Article(UUID.randomUUID(),
                "Обзор смартфонов 2024",
                "Лучшие смартфоны этого года");
        Article article3 = new Article(UUID.randomUUID(),
                "Ноутбук для программиста",
                "Какой ноутбук выбрать для программирования");
        Article article4 = new Article(UUID.randomUUID(),
                "Уход за наушниками",
                "Как продлить жизнь вашим наушникам");
        Article article5 = new Article(UUID.randomUUID(),
                "Игровые ноутбуки 2024 года",
                "Топ игровых ноутбуков 2024");

        articles.put(article1.getId(), article1);
        articles.put(article2.getId(), article2);
        articles.put(article3.getId(), article3);
        articles.put(article4.getId(), article4);
        articles.put(article5.getId(), article5);
    }

    public Collection<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    public Collection<Article> getAllArticles() {
        return new ArrayList<>(articles.values());
    }

    public Collection<Searchable> getAllSearchable() {
        Collection<Searchable> result = new ArrayList<>();
        result.addAll(products.values());
        result.addAll(articles.values());
        return result;
    }

    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(products.get(id));
    }
}