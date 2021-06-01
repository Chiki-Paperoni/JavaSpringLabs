package com.example.demo;

import com.example.demo.model.UserDao;
import domain.user.DUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DUserDaoTests {
    @Autowired
    private UserDao userDao = new UserDao();
    @Test
    public void indexShouldReturnFullListOfUsersTest() {
        List<DUser> DUsers = new ArrayList<>();
        DUsers = userDao.index();
        assertThat(DUsers.size() == 20).isTrue();
    }
    @Test
    public void WhenSaveNewRowShouldAppear_AndSHowCanFindIt_ThenDeleteTest() {
        DUser newDUser = new DUser(100,"TEST","TEST","TEST","TEST","TEST","TEST");
        userDao.save(newDUser);
        assertThat(userDao.show(100).getName()).isEqualTo("TEST");
        userDao.delete(100);
        assertThat(userDao.show(100)).isNull();
    }
    @Test
    public void WhenUpdateDataChangesTest() {
        DUser newDUser = new DUser(1,"TEST","TEST","TEST","TEST","TEST","TEST");
        userDao.update(1, newDUser);
        assertThat(userDao.show(1).getName()).isEqualTo("TEST");

    }
}
