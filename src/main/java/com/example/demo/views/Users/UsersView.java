package com.example.demo.views.Users;

import com.example.demo.model.UserDao;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.router.*;
import domain.user.User;

import org.springframework.web.bind.annotation.GetMapping;

import java.awt.*;

@Route(value = "users")
@PageTitle("Users")
public class UsersView  extends Div  implements HasUrlParameter<String>{
    private UserDao userDao = new UserDao();
    public UsersView () {
        addClassName("users-view");
    }
    @Override
    public void setParameter(BeforeEvent event,
                             @OptionalParameter String parameter) {
        if (parameter == null) {
            for (User user:userDao.index()) {
                add(new Paragraph(new Text( user.getName() + " " + user.getEmail() + " " + user.getPhone() + " " + user.getSurname() )));
            }
        } else {
            User user = userDao.show(Integer.parseInt(parameter));
            if (user != null) {
                add(new Paragraph(new Text( user.getName() + " " + user.getEmail() + " " + user.getPhone() + " " + user.getSurname() )));
            } else {
                add(new Text("No such User"));
            }
        }
    }
}