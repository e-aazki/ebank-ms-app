package com.enset.ebankbot.discord;

import com.enset.ebankbot.agents.EbankAIAgent;
import org.springframework.ai.chat.prompt.Prompt;

/*
import com.zgamelogic.discord.annotations.DiscordController;
import com.zgamelogic.discord.annotations.DiscordMapping;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
*/

//@DiscordController
public class DiscordBot {

    private EbankAIAgent ebankAIAgent;

    public DiscordBot(EbankAIAgent ebankAIAgent) {
        this.ebankAIAgent = ebankAIAgent;
    }
/*
    @DiscordMapping
    private void perform(MessageReceivedEvent event){
        System.out.println(event);
        if(event.getAuthor().isBot()) return;
        String query = event.getMessage().getContentRaw();
        String response = ebankAIAgent.chat(new Prompt(query)  );
        event.getChannel().sendMessage(response).queue();
    }*/
}
