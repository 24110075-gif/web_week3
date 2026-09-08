package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

        CartItem newItem = new CartItem(product, 1);
        items.add(newItem);
    }

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

    public void removeItem(String productId) {
        if (productId == null) {
            return;
        }

        items.removeIf(item -> item.getProduct() != null && productId.equalsIgnoreCase(item.getProduct().getId()));
    }

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
