# CD Store Web Application (Murach Chapter 7)

A Java Web Application built with **Jakarta Servlet 6.0**, **JSP 3.1**, and **Apache Tomcat 11**, demonstrating core web concepts:
- **HttpSession**: Managing a persistent shopping cart across user requests.
- **Cookies**: Tracking the user's `lastVisit` timestamp and displaying a personalized welcome banner.

---

## 🌟 Features
- **CD Catalog**: View list of available CD albums with descriptions and formatted pricing.
- **Shopping Cart**:
  - Add items to the cart.
  - Update quantities dynamically.
  - Remove items from the cart.
  - Live total calculation.
- **Cookie Demonstration**: Reads and writes HttpOnly `lastVisit` cookie.
- **Modern & Bright UI**: Styled with clean, modern CSS, Google Font (`Plus Jakarta Sans`), card layout, and responsive tables without external heavy CSS frameworks or external images.
- **Checkout Confirmation**: Completes the order and clears the session cart.

---

## 🛠 Tech Stack
- **Language**: Java 17+
- **Framework**: Jakarta EE 10 (Servlet 6.0, JSP 3.1)
- **Container**: Apache Tomcat 11
- **Build Tool**: Apache Maven
- **Deployment**: Docker / Render

---

## 🚀 How to Run Locally

### With Maven & Local Tomcat 11:
1. Package the WAR:
   ```bash
   mvn clean package
   ```
2. Copy `target/webapp_w2.war` to your Tomcat `webapps/` folder.
3. Access: `http://localhost:8080/webapp_w2/`

### With Docker:
```bash
docker build -t cd-store .
docker run -p 8080:8080 cd-store
```
Then open `http://localhost:8080/`

---

## ☁️ Deployment on Render
1. Create a new **Web Service** on [Render](https://render.com).
2. Connect your GitHub repository: `https://github.com/24110075-gif/web_week3`.
3. Render will automatically detect the `Dockerfile` and build/deploy the application.
