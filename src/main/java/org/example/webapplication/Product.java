package org.example.webapplication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
public class Product {
    String name;
    String date;
    Double price;
    Manufacturer manufacturer;


    public Product() {}
    public Product(String name, String date, Double price) {
        this.name = name;
        this.date = date;
        this.price = price;
    }
    record NameDatePrice(String name, String date){}

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", price=" + price +
                ", manufacturer=" + manufacturer +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return Objects.equals(name, product.name)
                && Objects.equals(date, product.date)
                && Objects.equals(price, product.price)
                && Objects.equals(manufacturer, product.manufacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, date, price, manufacturer);
    }
}
