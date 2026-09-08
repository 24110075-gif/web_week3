package model;

import java.io.Serializable;

/**
 * CartItem represents an item within the user's shopping cart.
 * Links a Product with a Quantity and calculates line item total.
 */
public class CartItem implements Serializable {
    private static final long serialVersionUID = 1L;

    private Product product;
    private int quantity;

    public CartItem() {
        this.product = new Product();
        this.quantity = 0;
    }

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        if (product != null) {
            return product.getPrice() * quantity;
        }
        return 0.0;
    }

    public String getTotalCurrencyFormat() {
        return String.format(java.util.Locale.US, "$%.2f", getTotal());
    }
}
