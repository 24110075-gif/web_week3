package servlet;

import data.ProductData;
import model.Cart;
import model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * CartServlet handles shopping cart operations (add, update, remove, view).
 * 
 * Chapter 7 Learning Objectives:
 * 1. HttpSession:
 *    The shopping cart is stored in HttpSession so that the server can associate
 *    the cart with the current user's session across multiple HTTP requests.
 * 
 * 2. Concurrency:
 *    Note on concurrency: HttpSession can be accessed by multiple requests/threads
 *    from the same browser session concurrently.
 * 
 * 3. Cookies:
 *    Cookies are stored on the browser and sent back to the server with subsequent requests.
 *    The cart itself should NOT be stored in the cookie.
 *    Only the simple lastVisit information should use a cookie.
 */
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

        // 1. Obtain or create the user's HttpSession
        // The shopping cart is stored in HttpSession so that the server can associate 
        // the cart with the current user's session across multiple HTTP requests.
        HttpSession session = request.getSession();

        // Retrieve existing cart or initialize a new one if not present
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        // 2. Read request parameters
        String action = request.getParameter("action");
        if (action == null) {
            action = "view";
        }

        String productId = request.getParameter("productId");
        if (productId == null || productId.trim().isEmpty()) {
            productId = request.getParameter("productCode"); // Support legacy productCode parameter
        }

        // 3. Process actions
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
                quantity = 1; // Default fallback on invalid input
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
            // "view" or default
            response.sendRedirect(request.getContextPath() + "/cart.jsp");
            return;
        }
    }
}
