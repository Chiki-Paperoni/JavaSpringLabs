package com.example.demo;

import com.example.demo.model.UserDao;
import domain.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDaoTests {
    @Autowired
    private UserDao userDao = new UserDao();
    @Test
    public void indexShouldReturnFullListOfUsersTest() {
        List<User> users = new ArrayList<>();
        users = userDao.index();
        assertThat(users.size() == 20).isTrue();
    }
    @Test
    public void WhenSaveNewRowShouldAppear_AndSHowCanFindIt_ThenDeleteTest() {
        User newUser = new User(100,"TEST","TEST","TEST","TEST","TEST","TEST");
        userDao.save(newUser);
        assertThat(userDao.show(100).getName()).isEqualTo("TEST");
        userDao.delete(100);
        assertThat(userDao.show(100)).isNull();
    }
    @Test
    public void WhenUpdateDataChangesTest() {
        User newUser = new User(1,"TEST","TEST","TEST","TEST","TEST","TEST");
        userDao.update(1,newUser);
        assertThat(userDao.show(1).getName()).isEqualTo("TEST");

    }
}
