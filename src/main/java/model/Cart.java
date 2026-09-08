package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Cart represents the shopping cart stored inside the user's HttpSession.
 * Manages the collection of CartItem objects across multiple HTTP requests.
 */
public class Cart implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<CartItem> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    /**
     * Adds a product to the shopping cart.
     * If the product already exists in the cart, increment its quantity by 1.
     * Otherwise, create a new CartItem with quantity 1.
     */
    public void addItem(Product product) {
        if (product == null || product.getId() == null) {
            return;
        }

        for (CartItem item : items) {
            if (item.getProduct() != null && product.getId().equalsIgnoreCase(item.getProduct().getId())) {
                item.setQuantity(item.getQuantity() + 1);
                return;
            }
        }

        // Product not in cart yet, add as new item
        CartItem newItem = new CartItem(product, 1);
        items.add(newItem);
    }

    /**
     * Updates the quantity for a specific product ID.
     * If the new quantity is <= 0, the item is removed from the cart.
     */
    public void updateQuantity(String productId, int quantity) {
        if (productId == null) {
            return;
        }

        for (int i = 0; i < items.size(); i++) {
            CartItem item = items.get(i);
            if (item.getProduct() != null && productId.equalsIgnoreCase(item.getProduct().getId())) {
                if (quantity <= 0) {
                    items.remove(i);
                } else {
                    item.setQuantity(quantity);
                }
                return;
            }
        }
    }

    /**
     * Removes an item from the cart matching the specified product ID.
     */
    public void removeItem(String productId) {
        if (productId == null) {
            return;
        }

        items.removeIf(item -> item.getProduct() != null && productId.equalsIgnoreCase(item.getProduct().getId()));
    }

    /**
     * Calculates the total amount of all items in the cart.
     */
    public double getTotal() {
        double total = 0.0;
        for (CartItem item : items) {
            total += item.getTotal();
        }
        return total;
    }

    public String getTotalCurrencyFormat() {
        return String.format(java.util.Locale.US, "$%.2f", getTotal());
    }

    /**
     * Calculates the total quantity of all items in the cart.
     */
    public int getTotalQuantity() {
        int totalQty = 0;
        for (CartItem item : items) {
            totalQty += item.getQuantity();
        }
        return totalQty;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}
