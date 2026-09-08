<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.Cart" %>
<%@ page import="model.CartItem" %>
<%
    // Chapter 7: HttpSession Demonstration
    // Retrieve the shopping cart from the user's session
    Cart cart = (Cart) session.getAttribute("cart");
%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Murach's Java Servlets and JSP</title>
    <link rel="stylesheet" type="text/css" href="main.css">
</head>

<body>

    <h2>Your cart</h2>

    <table>
        <thead>
            <tr>
                <th>Quantity</th>
                <th>Description</th>
                <th>Price</th>
                <th>Amount</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <% 
                if (cart != null && !cart.isEmpty()) { 
                    for (CartItem item : cart.getItems()) {
            %>
            <tr>
                <td>
                    <form action="cart" method="post">
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" name="productId" value="<%= item.getProduct().getId() %>">
                        <input type="hidden" name="productCode" value="<%= item.getProduct().getId() %>">
                        <input type="text" name="quantity" value="<%= item.getQuantity() %>">
                        <input type="submit" value="Update">
                    </form>
                </td>
                <td><%= item.getProduct().getDescription() %></td>
                <td><%= item.getProduct().getPriceCurrencyFormat() %></td>
                <td><%= item.getTotalCurrencyFormat() %></td>
                <td>
                    <form action="cart" method="post">
                        <input type="hidden" name="action" value="remove">
                        <input type="hidden" name="productId" value="<%= item.getProduct().getId() %>">
                        <input type="hidden" name="productCode" value="<%= item.getProduct().getId() %>">
                        <input type="submit" value="Remove Item">
                    </form>
                </td>
            </tr>
            <% 
                    }
            %>
            <tr class="total-row">
                <td colspan="3" style="text-align: right; border-right: none;"><strong>Total:</strong></td>
                <td colspan="2" style="border-left: none;"><strong><%= cart.getTotalCurrencyFormat() %></strong></td>
            </tr>
            <% 
                } else { 
            %>
            <tr>
                <td colspan="5" style="text-align: center; color: #666; padding: 20px;">
                    Your cart is currently empty.
                </td>
            </tr>
            <% 
                } 
            %>
        </tbody>
    </table>

    <p class="note"><strong>To change the quantity</strong>, enter the new quantity and click on the Update button.</p>

    <div class="action-buttons">
        <form action="index.jsp" method="get">
            <input type="submit" value="Continue Shopping">
        </form>
        <form action="checkout" method="post">
            <input type="hidden" name="action" value="checkout">
            <input type="submit" value="Checkout">
        </form>
    </div>

</body>

</html>
