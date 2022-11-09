package com.dao;

import com.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserDaoTest {

    private UserDao dao;
    private User user1;
    private User user2;
    private User user3;

    @BeforeEach
    public void setUp(){
        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
        this.dao = context.getBean("userDao", UserDao.class);

        this.user1 = new User("test1", "유재석", "springno1");
        this.user2 = new User("test2", "이수근", "springno2");
        this.user3 = new User("test3", "강호동", "springno3");
    }

    @Test
    public void addAndGet() throws SQLException {
        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.add(user1);
        dao.add(user2);
        assertThat(dao.getCount(), is(2));

        User result1 = dao.get(user1.getId());
        assertThat(result1.getName(), is(user1.getName()));
        assertThat(result1.getPassword(), is(user1.getPassword()));

        User result2 = dao.get(user2.getId());
        assertThat(result2.getName(), is(user2.getName()));
        assertThat(result2.getPassword(), is(user2.getPassword()));
    }

    @Test
    public void count() throws SQLException {
        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.add(user1);
        assertThat(dao.getCount(), is(1));

        dao.add(user2);
        assertThat(dao.getCount(), is(2));

        dao.add(user3);
        assertThat(dao.getCount(), is(3));
    }

    @Test
    public void getUserFailure() throws SQLException {
        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");

        UserDao dao = context.getBean("userDao", UserDao.class);
        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        // 이 메소드 실행 중에 예외가 발생해야 한다. 예외가 발생하지 않으면 테스트가 실패한다.
        assertThatThrownBy(() -> {
            dao.get("unkown_id");
        }).isInstanceOf(EmptyResultDataAccessException.class);
    }
}