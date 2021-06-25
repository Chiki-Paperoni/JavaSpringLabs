package com.example.demo.views.Users;

import com.example.demo.model.UserDao;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.router.*;
import domain.user.DUser;

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
//            for (DUser DUser :userDao.index()) {
//                add(new Paragraph(new Text( DUser.getName() + " " + DUser.getEmail() + " " + DUser.getPhone() + " " + DUser.getSurname() )));
//            }
        } else {
//            DUser DUser = userDao.show(Integer.parseInt(parameter));
//            if (DUser != null) {
//                add(new Paragraph(new Text( DUser.getName() + " " + DUser.getEmail() + " " + DUser.getPhone() + " " + DUser.getSurname() )));
//            } else {
//                add(new Text("No such User"));
//            }
        }
    }
}