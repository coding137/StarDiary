package com.example.cloud.bitmaptest;

/**
 * Created by Cloud on 2017-03-09.
 */

public class Item {
    private  int id;
    private String topic;
    private  String contents;
    private  byte[] image;

    public Item(int id, String topic, String contents, byte[] image) {
        this.id = id;
        this.topic = topic;
        this.contents = contents;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
