package com.dao;

public class DaoFactory {

    public UserDao userDao(){
        UserDao userDao = new UserDao(connectionMaker());
        return userDao;
    }

    public ConnectionMaker connectionMaker(){
        DConnectionMaker connectionMaker = new DConnectionMaker();
        return connectionMaker;
    }
}
