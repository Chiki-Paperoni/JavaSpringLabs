package com.example.demo.model;

import domain.user.DUser;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public List<DUser> index() {
        List<DUser> DUsers = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM users";
            ResultSet result = statement.executeQuery(SQL);
            while(result.next()) {
                DUser DUser = new DUser();
                DUser.setId(result.getInt("id"));
                DUser.setName(result.getString("fname"));
                DUser.setSurname(result.getString("surname"));
                DUser.setEmail(result.getString("email"));
                DUser.setPhone(result.getString("phone"));
                DUser.setPassword(result.getString("password"));
                DUsers.add(DUser);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return DUsers;
    }
    public void save(DUser DUser) {
        try {
            PreparedStatement prepare =
                    connection.prepareStatement("INSERT INTO users VALUES(100,?,?,?,?,?,?)");
            prepare.setString(1, DUser.getName());
            prepare.setString(2, DUser.getSurname());
            prepare.setString(3, DUser.getEmail());
            prepare.setString(4, DUser.getPhone());
            prepare.setString(5, DUser.getPassword());
            prepare.setString(6, DUser.getRoles());
            prepare.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public DUser show(int id) {
        DUser DUser = null;
        try {
            PreparedStatement prepare = connection.prepareStatement("SELECT * FROM users WHERE id=?");
            prepare.setInt(1,id);
            System.out.println(id);
            ResultSet result = prepare.executeQuery();
//            if (result.getFetchSize() != 0) {
                result.next();
                DUser = new DUser();
                DUser.setId(result.getInt("id"));
                DUser.setName(result.getString("fname"));
                DUser.setSurname(result.getString("surname"));
                DUser.setEmail(result.getString("email"));
                DUser.setPhone(result.getString("phone"));
                DUser.setPassword(result.getString("password"));
                DUser.setRoles(result.getString("roles"));
                return DUser;
//            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
//        return users.stream().filter(user -> user.getId() == id).findAny().orElse(null);
    }
    public int update(int id, DUser updated) {
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
            return id;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }
    public int delete(int id) {
        PreparedStatement prepare = null;

        try {
            prepare = connection.prepareStatement("DELETE FROM users WHERE id=?");
            prepare.setInt(1,id);
            prepare.executeUpdate();
            return id;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }
}
