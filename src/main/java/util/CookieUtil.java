package util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CookieUtil {

    private static final String COOKIE_NAME = "lastVisit";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

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

        String currentTime = LocalDateTime.now().format(FORMATTER);
        String encodedTime = URLEncoder.encode(currentTime, StandardCharsets.UTF_8);
        Cookie visitCookie = new Cookie(COOKIE_NAME, encodedTime);
        visitCookie.setMaxAge(60 * 60 * 24 * 30);
        visitCookie.setHttpOnly(true);
        visitCookie.setPath(request.getContextPath().isEmpty() ? "/" : request.getContextPath());
        response.addCookie(visitCookie);

        return welcomeMessage;
    }
}
