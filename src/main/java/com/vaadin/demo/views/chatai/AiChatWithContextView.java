package com.vaadin.demo.views.chatai;

import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.vaadin.firitin.components.messagelist.MarkdownMessage;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

import com.vaadin.demo.data.Talk;
import com.vaadin.demo.data.TalkRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.messages.MessageInput;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("AI Chat with Context")
@Menu(title = "AI Chat & Context", icon = LineAwesomeIconUrl.ROBOT_SOLID, order = 7)
@Route(value = "chat-ai-with-context")
public class AiChatWithContextView extends SplitLayout {

    private List<Talk> talks = TalkRepository.findAll();
    private Grid<Talk> grid;

    public AiChatWithContextView(ChatClient.Builder chatClientBuilder) {
        // Configure the split layout
        setOrientation(Orientation.VERTICAL);
        setSplitterPosition(66);
        setSizeFull();

        // Add a grid of conference talks
        grid = new Grid<>(Talk.class, false);
        grid.setSizeFull();
        grid.addColumn(Talk::getCategory).setHeader("Category");
        grid.addColumn(Talk::getTopic).setHeader("Topic");
        grid.addColumn(Talk::getSpeaker).setHeader("Speaker");
        grid.addColumn(Talk::getLanguage).setHeader("Language");
        grid.setSelectionMode(SelectionMode.MULTI);
        grid.setItems(talks);

        addToPrimary(new VerticalLayout(new H3("Conference Talks"), grid));

        // Create the chat client and message list
        ChatClient chatClient = chatClientBuilder.build();

        var messageList = new VerticalLayout();
        var messageInput = new MessageInput();

        messageInput.addSubmitListener(event -> {
            var question = event.getValue();
            var userMessage = new MarkdownMessage(question, "You", MarkdownMessage.Color.AVATAR_PRESETS[0]);
            var assistantMessage = new MarkdownMessage("Assistant", MarkdownMessage.Color.AVATAR_PRESETS[1]);

            messageList.add(userMessage, assistantMessage);

            chatClient.prompt().tools(this).user(question).stream().content()
                    .subscribe(assistantMessage::appendMarkdownAsync);
        });

        Scroller scroller = new Scroller(messageList);
        scroller.setSizeFull();

        Button clear = new Button("Clear");
        clear.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        clear.addClickListener(e -> {
            grid.getSelectionModel().deselectAll();
            messageList.removeAll();
        });

        HorizontalLayout wrapper = new HorizontalLayout();
        wrapper.setAlignItems(Alignment.BASELINE);
        wrapper.addAndExpand(messageInput);
        wrapper.add(clear);

        addToSecondary(new VerticalLayout(scroller, wrapper));
    }

    @Tool(description = "Get a list of all scheduled conference talks with their id, category, topic, speaker and language")
    List<Talk> getAllTalks() {
        return talks;
    }

    @Tool(description = "Highlight conference talks")
    void makeReservation(@ToolParam(description = "Id of the conference talk") String id) {
        Talk talk = talks.stream().filter(t -> t.getId() == Integer.parseInt(id)).findFirst().get();
        getUI().get().access(() -> grid.select(talk));
    }
}
