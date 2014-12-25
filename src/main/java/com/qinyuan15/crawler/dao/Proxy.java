package com.qinyuan15.crawler.dao;

/**
 * Created by qinyuan on 14-12-25.
 */
public class Proxy {

    private int id;
    private String host;
    private int port;
    private String type;
    private int speed;

    public int getId() {
        return id;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getType() {
        return type;
    }

    public int getSpeed() {
        return speed;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
