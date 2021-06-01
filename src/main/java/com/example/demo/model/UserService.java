package com.example.demo.model;

import domain.user.DUser;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class UserService {
    private UserDao users = new UserDao();

    public List<DUser> getUsers() {
        return users.index();
    }
    public void save(DUser DUser) {
        users.save(DUser);
    }
    public DUser getUser(int id) {
        return users.show(id);
    }
    public int updateUser(int id, DUser updated) {
        return users.update(id,updated);
    }
    public int delete(int id) {
        return users.delete(id);
    }
}
