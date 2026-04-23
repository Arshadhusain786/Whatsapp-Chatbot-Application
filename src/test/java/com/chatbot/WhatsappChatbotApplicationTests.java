package com.chatbot;

import com.chatbot.model.MessageRequest;
import com.chatbot.model.MessageResponse;
import com.chatbot.service.ChatbotService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WhatsappChatbotApplicationTests {

    @Autowired
    private ChatbotService chatbotService;

    @Test
    void contextLoads() {
        assertNotNull(chatbotService);
    }

    @Test
    void testHiReply() {
        MessageRequest req = new MessageRequest("919000000000", "Hi");
        MessageResponse res = chatbotService.processMessage(req);
        assertTrue(res.getReply().toLowerCase().contains("hello"));
    }

    @Test
    void testByeReply() {
        MessageRequest req = new MessageRequest("919000000000", "Bye");
        MessageResponse res = chatbotService.processMessage(req);
        assertTrue(res.getReply().toLowerCase().contains("goodbye") ||
                   res.getReply().toLowerCase().contains("bye"));
    }

    @Test
    void testCaseInsensitive() {
        MessageRequest req = new MessageRequest("919000000000", "HI");
        MessageResponse res = chatbotService.processMessage(req);
        assertTrue(res.getReply().toLowerCase().contains("hello"));
    }

    @Test
    void testUnknownMessageReturnsDefault() {
        MessageRequest req = new MessageRequest("919000000000", "xyzunknown123");
        MessageResponse res = chatbotService.processMessage(req);
        assertTrue(res.getReply().toLowerCase().contains("didn't understand") ||
                   res.getReply().toLowerCase().contains("help"));
    }

    @Test
    void testResponseContainsTimestamp() {
        MessageRequest req = new MessageRequest("919000000000", "Hello");
        MessageResponse res = chatbotService.processMessage(req);
        assertNotNull(res.getTimestamp());
    }
}
