package org.skypro.skyshop.model.product;

import org.skypro.skyshop.model.search.Searchable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Objects;
import java.util.UUID;

public abstract class Product implements Searchable {
    protected final UUID id;
    protected final String name;

    public Product(UUID id, String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Название продукта не может быть null или пустым");
        }
        this.id = id;
        this.name = name;
    }

    // ТОЛЬКО ЭТИ МЕТОДЫ БУДУТ В JSON
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public abstract double getPrice();
    public abstract boolean isSpecial();

    // ЭТИ МЕТОДЫ СКРЫВАЕМ
    @Override
    @JsonIgnore
    public String getSearchTerm() {
        return name;
    }

    @Override
    @JsonIgnore
    public String getContentType() {
        return "PRODUCT";
    }

    @Override
    @JsonIgnore
    public String getSearchableName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}