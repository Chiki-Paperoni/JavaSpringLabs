package com.example.demo.views.Users;

import com.example.demo.model.UserDao;
import com.example.demo.repos.UserEntity;
import com.example.demo.repos.UserRepo;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.router.*;
import domain.user.DUser;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "users")
@PageTitle("Users")
public class UsersView  extends Div  implements HasUrlParameter<String>{
    @Autowired
    UserRepo userRepo;
    private UserDao userDao = new UserDao();
    public UsersView () {
        addClassName("users-view");
    }
    @Override
    public void setParameter(BeforeEvent event,
                             @OptionalParameter String parameter) {

       //
        if (parameter == null) {
            Iterable<UserEntity> users = userRepo.findAll();
            for (UserEntity u :users) {
                add(new Paragraph("Login: " + u.getName() + " email: " + u.getEmail() + " Phone: " + u.getPhone() + " Surname: " + u.getSurname()));
            }
        } else {
//            DUser DUser = userDao.show(Integer.parseInt(parameter));
//            if (DUser != null) {
//                add(new Paragraph(new Text( DUser.getName() + " " + DUser.getEmail() + " " + DUser.getPhone() + " " + DUser.getSurname() )));
//            } else {
//                add(new Text("No such User"));
//            }
            UserEntity u = userRepo.findById(Integer.parseInt(parameter));
            if (u != null) {
                add(new Paragraph("Login: " + u.getName() + " email: " + u.getEmail() + " Phone: " + u.getPhone() + " Surname: " + u.getSurname()));
            }
        }
    }
}