package com.enset.ebankbot.agents;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Service;

@Service
public class EbankAIAgent {

    private final String conversationId="default-session";

    private ChatClient chatClient;

    public EbankAIAgent(ChatClient.Builder chatClient, ChatMemory chatMemory, ToolCallbackProvider tools) {
        this.chatClient = chatClient
                .defaultSystem("""
                        Vous êtes un assistant qui se charge de répondre aux question 
                        de l'utilisateur à propos des clients et des comptes bancaire, en fonction du contexte fourni. 
                        Si aucune contexte n'est fourni, répond avec JE NE SAIS PAS
                        """)
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .defaultTools(tools)
                .build();
    }


    public String chat(Prompt prompt) {
        // Ajout du paramètre conversationId (obligatoire pour l'advisor)
       // @RequestParam(name = "conversationId", defaultValue = "default-session") String conversationId


        //return chatClient.prompt(query).call().content();   old
        return chatClient.prompt(prompt)
                // On passe l'ID via le contexte de l'advisor
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, conversationId))
                .call()
                .content();
    }
}
