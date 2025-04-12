package file_signs.vms.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ChatController {
    
    private final ChatClient chatClient;     

    public ChatController(ChatClient.Builder builder) {
        this.chatClient = builder
                .build();
    }
    
    @GetMapping("/chat")
    public String chat(@RequestBody String question) {
        return chatClient.prompt()
                .user(question)    
                .call()
                .content();        
    }
}