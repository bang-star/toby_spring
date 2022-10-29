package com.dao;

import com.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDaoTest {

    @Test
    public void addAndGet() throws SQLException {
        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
        UserDao userDao = context.getBean("userDao", UserDao.class);

        User user = new User();
        user.setId("0");
        user.setName("seongminBang");
        user.setPassword("1234");

        userDao.add(user);

        System.out.println(user.getId() + " Enroll Success");

        User result = userDao.get("0");
        assertEquals("0", result.getId());

        if(!user.getName().equals(result.getName())){
            System.out.println("테스트 실패 (name)");
        }else if(!user.getPassword().equals(result.getPassword())){
            System.out.println("테스트 실패 (password)");
        }else{
            System.out.println("조회 테스트 성공");
        }
    }

    @Test
    public void checkIdentity() {

        DaoFactory factory = new DaoFactory();
        UserDao dao1 = factory.userDao();
        UserDao dao2 = factory.userDao();

        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao dao3 = context.getBean("userDao", UserDao.class);
        UserDao dao4 = context.getBean("userDao", UserDao.class);

        System.out.println("dao1 - "+dao1);
        System.out.println("dao2 - "+dao2);
        System.out.println("dao3 - "+dao3);
        System.out.println("dao4 - "+dao4);
    }
}