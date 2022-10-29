package com.dao;

import com.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 1.7.4 의존관계 주입의 응용 - 기능 추가(커넥션 횟수 카운트 기능)
 * 커넥션 카운트 기능의 TestCase
 */
@Deprecated
public class UserDaoConnectionCountingTest {

    @DisplayName("CountingConnectionMaker에 대한 테스트")
    @Test
    public void counting() throws SQLException{
        ApplicationContext context = new AnnotationConfigApplicationContext(CountingDaoFactory.class);
        UserDao userDao = context.getBean("userDao", UserDao.class);

        // DAO 사용 코드
        CountingConnectionMaker connectionMaker = context.getBean("connectionMaker", CountingConnectionMaker.class);
        User result = userDao.get("0");
        assertEquals("0", result.getId());

        System.out.println("Connection counter : "  + connectionMaker.getCounter());
    }

}
