package com.chatbot.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the bot's outgoing reply.
 */
public class MessageResponse {

    private String to;
    private String reply;
    private String timestamp;
    private String status;

    // --- Constructors ---
    public MessageResponse() {}

    public MessageResponse(String to, String reply) {
        this.to = to;
        this.reply = reply;
        this.timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.status = "delivered";
    }

    // --- Getters & Setters ---
    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
