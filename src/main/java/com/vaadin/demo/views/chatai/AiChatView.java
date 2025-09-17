package com.vaadin.demo.views.chatai;

import org.springframework.ai.chat.client.ChatClient;
import org.vaadin.firitin.components.messagelist.MarkdownMessage;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

import com.vaadin.flow.component.messages.MessageInput;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("AI Chat")
@Menu(title = "AI Chat", icon = LineAwesomeIconUrl.ROBOT_SOLID, order = 6)
@Route(value = "chat-ai")
public class AiChatView extends VerticalLayout {

    public AiChatView(ChatClient.Builder chatClientBuilder) {
        setSizeFull();

        ChatClient chatClient = chatClientBuilder.build();
        var messageList = new VerticalLayout();
        var messageInput = new MessageInput();
        messageInput.setWidthFull();

        messageInput.addSubmitListener(event -> {
            var question = event.getValue();
            var userMessage = new MarkdownMessage(question, "You", MarkdownMessage.Color.AVATAR_PRESETS[0]);
            var assistantMessage = new MarkdownMessage("Assistant", MarkdownMessage.Color.AVATAR_PRESETS[1]);

            messageList.add(userMessage, assistantMessage);

            chatClient.prompt().user(question).stream().content()
                    .subscribe(assistantMessage::appendMarkdownAsync);
        });

        addAndExpand(new Scroller(messageList));
        add(messageInput);
    }
}
