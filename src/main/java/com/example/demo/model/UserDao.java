package com.example.demo.model;

import com.vaadin.flow.component.html.Pre;
import domain.user.Role;
import domain.user.User;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class UserDao {
//    private List<User> users;
    private static final String URL = "jdbc:postgresql://localhost:5432/JavaLabsDB";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "password";

    private static Connection connection;
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
//    {
//        users = new ArrayList<>();
//
//        users.add(new User(1,"User1","User1","@email1","380991938475","password",new ArrayList<Role>()));
//        users.add(new User(2,"User2","User2","@email1","380991938475","password",new ArrayList<Role>()));
//        users.add(new User(3,"User3","User3","@email1","380991938475","password",new ArrayList<Role>()));
//        users.add(new User(4,"User4","User4","@email1","380991938475","password",new ArrayList<Role>()));
//        users.add(new User(5,"User5","User5","@email1","380991938475","password",new ArrayList<Role>()));
//        users.add(new User(6,"User6","User6","@email1","380991938475","password",new ArrayList<Role>()));
//        users.add(new User(7,"User7","User7","@email1","380991938475","password",new ArrayList<Role>()));
//        users.add(new User(8,"User8","User8","@email1","380991938475","password",new ArrayList<Role>()));
//        users.add(new User(9,"User9","User9","@email1","380991938475","password",new ArrayList<Role>()));
//    }

    public List<User> index() {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM users";
            ResultSet result = statement.executeQuery(SQL);
            while(result.next()) {
                User user = new User();
                user.setId(result.getInt("id"));
                user.setName(result.getString("fname"));
                user.setSurname(result.getString("surname"));
                user.setEmail(result.getString("email"));
                user.setPhone(result.getString("phone"));
                user.setPassword(result.getString("password"));
                users.add(user);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }
    public void save(User user) {
        try {
            PreparedStatement prepare =
                    connection.prepareStatement("INSERT INTO users VALUES(100,?,?,?,?,?,?)");
            prepare.setString(1,user.getName());
            prepare.setString(2,user.getSurname());
            prepare.setString(3,user.getEmail());
            prepare.setString(4,user.getPhone());
            prepare.setString(5,user.getPassword());
            prepare.setString(6,user.getRoles());


            prepare.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public User show(int id) {
        User user = null;
        try {
            PreparedStatement prepare = connection.prepareStatement("SELECT * FROM users WHERE id=?");
            prepare.setInt(1,id);
            System.out.println(id);
            ResultSet result = prepare.executeQuery();
//            if (result.getFetchSize() != 0) {
                result.next();
                user = new User();
                user.setId(result.getInt("id"));
                user.setName(result.getString("fname"));
                user.setSurname(result.getString("surname"));
                user.setEmail(result.getString("email"));
                user.setPhone(result.getString("phone"));
                user.setPassword(result.getString("password"));
                user.setRoles(result.getString("roles"));
                return user;
//            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
//        return users.stream().filter(user -> user.getId() == id).findAny().orElse(null);
    }
    public void update(int id, User updated) {
        try {
            PreparedStatement prepare = connection.prepareStatement("UPDATE users SET fname=?, surname=?, email=?, phone=?, password=?, roles=? WHERE id=?");
            prepare.setString(1,updated.getName());
            prepare.setString(2,updated.getSurname());
            prepare.setString(3,updated.getEmail());
            prepare.setString(4,updated.getPhone());
            prepare.setString(5,updated.getPassword());
            prepare.setString(6,updated.getRoles());
            prepare.setInt(7,id);
            prepare.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void delete(int id) {
        PreparedStatement prepare = null;

        try {
            prepare = connection.prepareStatement("DELETE FROM users WHERE id=?");
            prepare.setInt(1,id);
            prepare.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
