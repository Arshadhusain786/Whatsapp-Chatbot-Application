package com.chatbot.service;

import com.chatbot.model.MessageRequest;
import com.chatbot.model.MessageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Core chatbot logic.
 * Handles predefined keyword replies and logs every incoming message.
 */
@Service
public class ChatbotService {

    private static final Logger logger = LoggerFactory.getLogger(ChatbotService.class);

    // ---------------------------------------------------------------
    // Predefined reply map — add more keywords here anytime
    // ---------------------------------------------------------------
    private static final Map<String, String> REPLY_MAP = new HashMap<>();

    static {
        REPLY_MAP.put("hi",      "Hello! 👋 How can I help you today?");
        REPLY_MAP.put("hello",   "Hello! 👋 How can I help you today?");
        REPLY_MAP.put("hey",     "Hey there! 😊 What can I do for you?");
        REPLY_MAP.put("bye",     "Goodbye! 👋 Have a wonderful day!");
        REPLY_MAP.put("goodbye", "Goodbye! 👋 Take care!");
        REPLY_MAP.put("help",    "Sure! You can say: Hi, Help, About, Hours, Bye.");
        REPLY_MAP.put("about",   "I'm a WhatsApp Bot built with Java & Spring Boot. 🤖");
        REPLY_MAP.put("hours",   "We're available 24/7! 🕐");
        REPLY_MAP.put("thanks",  "You're welcome! 😊");
        REPLY_MAP.put("thank you","You're welcome! 😊");
    }

    // ---------------------------------------------------------------
    // Default fallback reply
    // ---------------------------------------------------------------
    private static final String DEFAULT_REPLY =
            "Sorry, I didn't understand that. Type 'Help' to see available commands.";

    /**
     * Processes an incoming message, logs it, and returns a bot reply.
     *
     * @param request the incoming WhatsApp message payload
     * @return MessageResponse containing the bot's reply
     */
    public MessageResponse processMessage(MessageRequest request) {

        // ── LOG every incoming message ──────────────────────────────
        logger.info("📩 Incoming message | From: {} | Message: \"{}\"",
                request.getFrom(), request.getMessage());

        // ── Normalize input (trim + lowercase for matching) ─────────
        String normalizedMessage = request.getMessage().trim().toLowerCase();

        // ── Look up reply ────────────────────────────────────────────
        String reply = REPLY_MAP.getOrDefault(normalizedMessage, DEFAULT_REPLY);

        // ── LOG the reply being sent ─────────────────────────────────
        logger.info("📤 Bot reply       | To:   {} | Reply:   \"{}\"",
                request.getFrom(), reply);

        return new MessageResponse(request.getFrom(), reply);
    }
}
