package servlet;

import model.Cart;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * CheckoutServlet displays order confirmation and completes the order.
 */
@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
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

        double orderTotal = 0.0;
        if (cart != null) {
            orderTotal = cart.getTotal();
        }

        request.setAttribute("orderTotal", String.format(java.util.Locale.US, "$%.2f", orderTotal));
        
        // Forward to checkout.jsp for rendering
        request.getRequestDispatcher("/checkout.jsp").forward(request, response);
    }
}
