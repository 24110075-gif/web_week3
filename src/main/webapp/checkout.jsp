<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.Cart" %>
<%@ page import="model.CartItem" %>
<%
    Cart cart = (Cart) session.getAttribute("cart");
    String orderTotal = (String) request.getAttribute("orderTotal");
    if (orderTotal == null && cart != null) {
        orderTotal = cart.getTotalCurrencyFormat();
    } else if (orderTotal == null) {
        orderTotal = "$0.00";
    }
%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Murach's Java Servlets and JSP</title>
    <link rel="stylesheet" type="text/css" href="main.css">
</head>

<body>

    <h2>Checkout Confirmation</h2>

    <h3>Thank you for your order!</h3>

    <p>Your order has been placed successfully.</p>

    <% if (cart != null && !cart.isEmpty()) { %>
    <table>
        <thead>
            <tr>
                <th>Quantity</th>
                <th>Description</th>
                <th>Price</th>
                <th>Amount</th>
            </tr>
        </thead>
        <tbody>
            <% for (CartItem item : cart.getItems()) { %>
            <tr>
                <td><%= item.getQuantity() %></td>
                <td><%= item.getProduct().getDescription() %></td>
                <td><%= item.getProduct().getPriceCurrencyFormat() %></td>
                <td><%= item.getTotalCurrencyFormat() %></td>
            </tr>
            <% } %>
            <tr class="total-row">
                <td colspan="3" style="text-align: right;"><strong>Total Paid:</strong></td>
                <td><strong><%= orderTotal %></strong></td>
            </tr>
        </tbody>
    </table>
    <% 
        // Order completed, remove cart from session
        session.removeAttribute("cart");
    } else { %>
    <p><strong>Total Amount: <%= orderTotal %></strong></p>
    <% } %>

    <div class="action-buttons">
        <form action="index.jsp" method="get">
            <input type="submit" value="Continue Shopping">
        </form>
    </div>

</body>

</html>
