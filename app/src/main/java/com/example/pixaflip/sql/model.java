package com.example.pixaflip.sql;

public class model {
    private int id;
    private String from1;
    private String to1;
    private String timestamp;

    public model()
    {
        this.id = id;
        this.from1 = from1;
        this.to1 = to1;
        this.timestamp = timestamp;
    }
    public model(int id, String from1, String to1, String timestamp) {
        this.id = id;
        this.from1 = from1;
        this.to1 = to1;
        this.timestamp = timestamp;
    }


    public model(String from1) {
        this.from1 = from1;
    }

    public model(String from1, String to1, String timestamp) {
        this.from1 = from1;
        this.to1 = to1;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFrom1() {
        return from1;
    }

    public String setFrom1(String from1) {
        this.from1 = from1;
        return from1;
    }

    public String getTo1() {
        return to1;
    }

    public String setTo1(String to1) {
        this.to1 = to1;
        return to1;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String setTimestamp(String timestamp) {
        this.timestamp = timestamp;
        return timestamp;
    }
}
