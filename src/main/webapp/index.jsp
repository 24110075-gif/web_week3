<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="util.CookieUtil" %>
<%@ page import="data.ProductData" %>
<%@ page import="model.Product" %>
<%@ page import="java.util.List" %>
<%
    // Chapter 7: Cookie Demonstration
    // Check if the "lastVisit" cookie exists and update it with the current timestamp
    String welcomeMsg = CookieUtil.getWelcomeMessageAndUpdate(request, response);
    List<Product> products = ProductData.getProducts();
%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Murach's Java Servlets and JSP</title>
    <link rel="stylesheet" type="text/css" href="main.css">
</head>

<body>

    <!-- Cookie demonstration welcome banner -->
    <div class="welcome-message">
        <%= welcomeMsg %>
    </div>

    <h2>CD list</h2>

    <table>
        <thead>
            <tr>
                <th>Description</th>
                <th>Price</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <% for (Product p : products) { %>
            <tr>
                <td><%= p.getDescription() %></td>
                <td><%= p.getPriceCurrencyFormat() %></td>
                <td>
                    <form action="cart" method="post">
                        <input type="hidden" name="action" value="add">
                        <input type="hidden" name="productId" value="<%= p.getId() %>">
                        <input type="hidden" name="productCode" value="<%= p.getId() %>">
                        <input type="submit" value="Add To Cart">
                    </form>
                </td>
            </tr>
            <% } %>
        </tbody>
    </table>

</body>

</html>
