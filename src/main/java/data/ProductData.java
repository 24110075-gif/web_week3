package data;

import model.Product;
import java.util.ArrayList;
import java.util.List;

/**
 * ProductData provides hardcoded CD product catalog in memory (no external DB required).
 */
public class ProductData {

    private static final List<Product> products = new ArrayList<>();

    static {
        // Four exact products from Chapter 7 reference screenshot
        products.add(new Product("CD001", "86 (the band) - True Life Songs and Pictures", 14.95));
        products.add(new Product("CD002", "Paddlefoot - The first CD", 12.95));
        products.add(new Product("CD003", "Paddlefoot - The second CD", 14.95));
        products.add(new Product("CD004", "Joe Rut - Genuine Wood Grained Finish", 14.95));
    }

    /**
     * Returns all available CD products.
     */
    public static List<Product> getProducts() {
        return products;
    }

    /**
     * Looks up a product by ID. Supports standard IDs (CD001-CD004) and legacy codes (8601, pf01, pf02, jr01).
     */
    public static Product getProductById(String id) {
        if (id == null) {
            return null;
        }

        // Support aliases
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
