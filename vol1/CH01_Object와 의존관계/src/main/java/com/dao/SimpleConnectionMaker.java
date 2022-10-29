package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 합성 방식의 분리 - db connection 관심 class
 * interface 방식을 쓰면서 직접적인 합성방식은 deprecated 되었다.
 */
@Deprecated
public class SimpleConnectionMaker {

    public Connection makeConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost/spring_book", "root", "root");
        return c;
    }
}
