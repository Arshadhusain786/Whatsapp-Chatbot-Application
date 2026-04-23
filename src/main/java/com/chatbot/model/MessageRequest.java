package com.chatbot.model;

import jakarta.validation.constraints.NotBlank;

/**
 * Represents an incoming WhatsApp-style message payload.
 * Example JSON:
 * {
 *   "from": "918888888888",
 *   "message": "Hi"
 * }
 */
public class MessageRequest {

    @NotBlank(message = "Sender number (from) is required")
    private String from;

    @NotBlank(message = "Message body cannot be blank")
    private String message;

    // --- Constructors ---
    public MessageRequest() {}

    public MessageRequest(String from, String message) {
        this.from = from;
        this.message = message;
    }

    // --- Getters & Setters ---
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MessageRequest{from='" + from + "', message='" + message + "'}";
    }
}
