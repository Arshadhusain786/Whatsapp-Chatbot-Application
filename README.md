# WhatsApp Chatbot Backend — Spring Boot

A **REST API simulation** of a WhatsApp chatbot backend built with **Java 17 + Spring Boot 3**.

---

## 📁 Project Structure

```
whatsapp-chatbot/
├── pom.xml
└── src/
    └── main/
        ├── java/com/chatbot/
        │   ├── WhatsappChatbotApplication.java   ← Entry point
        │   ├── controller/
        │   │   └── WebhookController.java        ← POST /webhook
        │   ├── service/
        │   │   └── ChatbotService.java           ← Reply logic + logging
        │   └── model/
        │       ├── MessageRequest.java           ← Input DTO
        │       └── MessageResponse.java          ← Output DTO
        └── resources/
            └── application.properties
```

---

## ▶️ How to Run Locally (IntelliJ)

1. Open IntelliJ → **File → Open** → select the `whatsapp-chatbot` folder
2. Wait for Maven to download dependencies
3. Run `WhatsappChatbotApplication.java` (main class)
4. Server starts at **http://localhost:8080**

---

## 🧪 Test with cURL

### Health Check (GET)
```bash
curl http://localhost:8080/webhook
```

### Send "Hi"
```bash
curl -X POST http://localhost:8080/webhook \
  -H "Content-Type: application/json" \
  -d '{"from":"919999999999","message":"Hi"}'
```
**Response:**
```json
{
  "to": "919999999999",
  "reply": "Hello! 👋 How can I help you today?",
  "timestamp": "2026-04-23 10:30:00",
  "status": "delivered"
}
```

### Send "Bye"
```bash
curl -X POST http://localhost:8080/webhook \
  -H "Content-Type: application/json" \
  -d '{"from":"919999999999","message":"Bye"}'
```

### Other Supported Keywords
| You Send  | Bot Replies                                      |
|-----------|--------------------------------------------------|
| Hi / Hello / Hey | Hello! 👋 How can I help you today?     |
| Bye / Goodbye    | Goodbye! 👋 Have a wonderful day!       |
| Help             | Available commands list                  |
| About            | Bot introduction                         |
| Hours            | Availability info                         |
| Thanks           | You're welcome!                          |
| *(anything else)*| Default fallback message                 |

---

## 📋 Console Logs (Example)

```
INFO  c.c.service.ChatbotService : 📩 Incoming message | From: 919999999999 | Message: "Hi"
INFO  c.c.service.ChatbotService : 📤 Bot reply        | To:   919999999999 | Reply:   "Hello! 👋 How can I help you today?"
```

---

## ☁️ Deploy on Render (Bonus)

1. Push this project to a **GitHub repo**
2. Go to [render.com](https://render.com) → New → **Web Service**
3. Connect your GitHub repo
4. Set these values:
   - **Environment:** `Java`
   - **Build Command:** `./mvnw clean package -DskipTests`
   - **Start Command:** `java -jar target/whatsapp-chatbot-0.0.1-SNAPSHOT.jar`
5. Click **Deploy** — Render gives a public URL like `https://your-app.onrender.com`
6. Test: `POST https://your-app.onrender.com/webhook`

> ⚠️ Add a `mvnw` wrapper if deploying to Render. Generate it via:
> `mvn wrapper:wrapper` inside the project directory.

---

## 🛠️ Tech Stack

- Java 17
- Spring Boot 3.2.4
- Spring Web + Validation
- SLF4J Logging
- Maven

---

*Built for internship assignment — Aashika | Deadline: 25 April 2026*
