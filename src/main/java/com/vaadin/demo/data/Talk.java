package com.vaadin.demo.data;

public class Talk {

    private int id;
    private String category;
    private String speaker;
    private String topic;
    private String language;

    public Talk(int id, String category, String topic, String speaker, String language) {
        this.id = id;
        this.category = category;
        this.topic = topic;
        this.speaker = speaker;
        this.language = language;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
