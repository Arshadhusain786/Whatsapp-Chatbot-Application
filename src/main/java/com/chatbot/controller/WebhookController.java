package com.chatbot.controller;

import com.chatbot.model.MessageRequest;
import com.chatbot.model.MessageResponse;
import com.chatbot.service.ChatbotService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * REST Controller exposing the /webhook endpoint.
 *
 * Endpoints:
 *   POST /webhook  → receives a WhatsApp-style message, returns bot reply
 *   GET  /webhook  → health check / verification ping
 */
@RestController
@RequestMapping("/webhook")
public class WebhookController {

    private static final Logger logger = LoggerFactory.getLogger(WebhookController.class);

    private final ChatbotService chatbotService;

    public WebhookController(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }

    // ----------------------------------------------------------------
    // POST /webhook — main message-receive endpoint
    // ----------------------------------------------------------------
    /**
     * Accepts a WhatsApp-simulated JSON message and returns a bot reply.
     *
     * Sample cURL:
     *   curl -X POST http://localhost:8080/webhook \
     *        -H "Content-Type: application/json" \
     *        -d '{"from":"919999999999","message":"Hi"}'
     */
    @PostMapping
    public ResponseEntity<MessageResponse> receiveMessage(
            @Valid @RequestBody MessageRequest request) {

        logger.info("▶ POST /webhook called");

        MessageResponse response = chatbotService.processMessage(request);

        return ResponseEntity.ok(response);
    }

    // ----------------------------------------------------------------
    // GET /webhook — verification / health-check endpoint
    // ----------------------------------------------------------------
    /**
     * Simple GET ping — useful for Render health checks and quick browser tests.
     */
    @GetMapping
    public ResponseEntity<Map<String, String>> healthCheck() {
        logger.info("▶ GET /webhook — health check");
        return ResponseEntity.ok(Map.of(
                "status",  "running",
                "service", "WhatsApp Chatbot Backend",
                "message", "Send a POST request with JSON body to interact with the bot."
        ));
    }
}
