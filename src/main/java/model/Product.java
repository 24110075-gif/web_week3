package model;

import java.io.Serializable;

public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String description;
    private double price;

    public Product() {
        this.id = "";
        this.description = "";
        this.price = 0.0;
    }

    public Product(String id, String description, double price) {
        this.id = id;
        this.description = description;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPriceCurrencyFormat() {
        return String.format(java.util.Locale.US, "$%.2f", price);
    }
}
