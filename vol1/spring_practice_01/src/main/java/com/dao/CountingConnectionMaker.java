package com.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class CountingConnectionMaker implements ConnectionMaker{
    int counter = 0;
    private ConnectionMaker realconnectionMaker;

    public CountingConnectionMaker(ConnectionMaker realconnectionMaker) {
        this.realconnectionMaker = realconnectionMaker;
    }

    @Override
    public Connection makeConnection() throws ClassNotFoundException, SQLException {
        this.counter++;
        return realconnectionMaker.makeConnection();
    }

    public int getCounter() {
        return this.counter;
    }
}
