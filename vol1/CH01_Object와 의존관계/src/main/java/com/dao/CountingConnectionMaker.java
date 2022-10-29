package com.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * ConnectionMaker 인터페이스 도입으로 인해 확장이 가능해졌다.
 */
@Deprecated
public class CountingConnectionMaker implements ConnectionMaker{
    private int counter = 0;
    private ConnectionMaker realConnectionMaker;

    public CountingConnectionMaker(ConnectionMaker realConnectionMaker) {
        this.realConnectionMaker = realConnectionMaker;
    }

    @Override
    public Connection makeConnection() throws ClassNotFoundException, SQLException {
        this.counter++;
        return realConnectionMaker.makeConnection();
    }

    public int getCounter() {
        return this.counter;
    }
}
