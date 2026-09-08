package util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * CookieUtil provides simple helper methods to demonstrate Cookies (Chapter 7).
 * Cookies are stored on the client browser and sent with subsequent requests.
 * The cart itself is NOT stored in cookies, only simple non-sensitive metadata.
 */
public class CookieUtil {

    private static final String COOKIE_NAME = "lastVisit";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Checks for the lastVisit cookie and returns a welcome message.
     * Also updates the cookie with the current date/time.
     */
    public static String getWelcomeMessageAndUpdate(HttpServletRequest request, HttpServletResponse response) {
        String welcomeMessage = "Welcome to the CD Store!";

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (COOKIE_NAME.equals(c.getName())) {
                    try {
                        String decodedTime = URLDecoder.decode(c.getValue(), StandardCharsets.UTF_8);
                        welcomeMessage = "Welcome back! Your last visit was: " + decodedTime;
                    } catch (Exception e) {
                        welcomeMessage = "Welcome back to the CD Store!";
                    }
                    break;
                }
            }
        }

        // Create / Update cookie with current timestamp
        String currentTime = LocalDateTime.now().format(FORMATTER);
        String encodedTime = URLEncoder.encode(currentTime, StandardCharsets.UTF_8);
        Cookie visitCookie = new Cookie(COOKIE_NAME, encodedTime);
        visitCookie.setMaxAge(60 * 60 * 24 * 30); // 30 days
        visitCookie.setHttpOnly(true); // Security best practice: prevent XSS access
        visitCookie.setPath(request.getContextPath().isEmpty() ? "/" : request.getContextPath());
        response.addCookie(visitCookie);

        return welcomeMessage;
    }
}
