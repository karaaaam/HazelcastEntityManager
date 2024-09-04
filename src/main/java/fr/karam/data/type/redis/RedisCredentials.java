package fr.karam.data.type.redis;

import fr.karam.data.type.DataCredentials;

public class RedisCredentials extends DataCredentials {

    private String ip;
    private int port;
    private String password;
    private int database;

    public RedisCredentials(String ip, int port, String password, int database) {
        this.ip = ip;
        this.port = port;
        this.password = password;
        this.database = database;
    }

    public String getIp() {
        return ip;
    }

    public RedisCredentials setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public int getPort() {
        return port;
    }

    public RedisCredentials setPort(int port) {
        this.port = port;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RedisCredentials setPassword(String password) {
        this.password = password;
        return this;
    }

    public int getDatabase() {
        return database;
    }

    public RedisCredentials setDatabase(int database) {
        this.database = database;
        return this;
    }
}
