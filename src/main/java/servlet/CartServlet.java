package servlet;

import data.ProductData;
import model.Cart;
import model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        String action = request.getParameter("action");
        if (action == null) {
            action = "view";
        }

        String productId = request.getParameter("productId");
        if (productId == null || productId.trim().isEmpty()) {
            productId = request.getParameter("productCode");
        }

        if ("add".equalsIgnoreCase(action)) {
            if (productId != null) {
                Product product = ProductData.getProductById(productId);
                if (product != null) {
                    cart.addItem(product);
                }
            }
            response.sendRedirect(request.getContextPath() + "/cart.jsp");
            return;

        } else if ("update".equalsIgnoreCase(action)) {
            String quantityParam = request.getParameter("quantity");
            int quantity = 1;
            try {
                if (quantityParam != null) {
                    quantity = Integer.parseInt(quantityParam.trim());
                }
            } catch (NumberFormatException e) {
                quantity = 1;
            }

            if (productId != null) {
                cart.updateQuantity(productId, quantity);
            }
            response.sendRedirect(request.getContextPath() + "/cart.jsp");
            return;

        } else if ("remove".equalsIgnoreCase(action)) {
            if (productId != null) {
                cart.removeItem(productId);
            }
            response.sendRedirect(request.getContextPath() + "/cart.jsp");
            return;

        } else if ("checkout".equalsIgnoreCase(action)) {
            response.sendRedirect(request.getContextPath() + "/checkout");
            return;

        } else {
            response.sendRedirect(request.getContextPath() + "/cart.jsp");
            return;
        }
    }
}
