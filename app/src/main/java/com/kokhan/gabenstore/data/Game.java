package com.kokhan.gabenstore.data;

public class Game {

    private String title;
    private String description;
    private int thumbnail;
    private int count;
    private int price;

    public Game(String title, String description, int thumbnail, int count, int price) {
        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
        this.count = count;
        this.price = price;
    }

    public Game() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
