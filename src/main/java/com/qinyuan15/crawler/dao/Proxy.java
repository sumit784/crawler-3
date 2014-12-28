package com.qinyuan15.crawler.dao;

/**
 * Proxy Server
 * Created by qinyuan on 14-12-25.
 */
public class Proxy extends PersistObject {

    private String host;
    private Integer port;
    private String type;
    private Integer speed;

    public String getHost() {
        return host;
    }

    public Integer getPort() {
        return port;
    }

    public String getType() {
        return type;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }
}
