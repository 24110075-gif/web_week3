package data;

import model.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductData {

    private static final List<Product> products = new ArrayList<>();

    static {
        products.add(new Product("CD001", "86 (the band) - True Life Songs and Pictures", 14.95));
        products.add(new Product("CD002", "Paddlefoot - The first CD", 12.95));
        products.add(new Product("CD003", "Paddlefoot - The second CD", 14.95));
        products.add(new Product("CD004", "Joe Rut - Genuine Wood Grained Finish", 14.95));
    }

    public static List<Product> getProducts() {
        return products;
    }

    public static Product getProductById(String id) {
        if (id == null) {
            return null;
        }

        String normalizedId = id.trim();
        if (normalizedId.equalsIgnoreCase("8601")) normalizedId = "CD001";
        else if (normalizedId.equalsIgnoreCase("pf01")) normalizedId = "CD002";
        else if (normalizedId.equalsIgnoreCase("pf02")) normalizedId = "CD003";
        else if (normalizedId.equalsIgnoreCase("jr01")) normalizedId = "CD004";

        for (Product product : products) {
            if (product.getId().equalsIgnoreCase(normalizedId)) {
                return product;
            }
        }
        return null;
    }
}
