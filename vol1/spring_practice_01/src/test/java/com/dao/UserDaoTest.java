package com.dao;

import com.domain.User;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDaoTest {

    @Test
    public void addAndGet() throws SQLException, ClassNotFoundException {
        UserDao userDao = new DaoFactory().userDao();

        User user = new User();
        user.setId("0");
        user.setName("seongminBang");
        user.setPassword("1234");

        userDao.add(user);

        System.out.println(user.getId() + " Enroll Success");

        User result = userDao.get("0");
        assertEquals("0", result.getId());

        System.out.println(result.getName());
        System.out.println(result.getPassword());

        System.out.println(result.getId()+" Search Success");
    }
}