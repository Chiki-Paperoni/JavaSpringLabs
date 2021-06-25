package com.example.demo.views.Users;

import com.example.demo.model.UserService;
import com.example.demo.repos.UserEntity;
import com.example.demo.repos.UserRepo;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import domain.user.DUser;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "/signup")
@PageTitle("Home")
public class SignUpView extends FormLayout implements BeforeEnterObserver {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;

    private Binder<DUser> binder = new Binder<>(DUser.class);

    private TextField name = new TextField("First name");
    private TextField surname = new TextField("Last name");
    private DatePicker birthDate = new DatePicker("Birthdate");
    private TextField phone = new TextField("Phone");
    private TextField email = new TextField("Email");
    private TextField password = new TextField("Choose password");

    private Button save = new Button("Confirm");
//    private Button delete = new Button("Delete");

    SignUpView() {
        HorizontalLayout buttons = new HorizontalLayout(save);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(name, surname, birthDate,email,phone,password, buttons);


        save.addClickListener(event -> save());
        binder.bindInstanceFields(this);
        binder.setBean(new DUser());
    }
    private void save() {
        UserEntity u = new UserEntity();
        u.setEmail(email.getValue());
        u.setName(name.getValue());
        u.setPassword(password.getValue());
        u.setSurname(surname.getValue());
        u.setPhone(phone.getValue());
        userService.save(binder.getBean());
        userRepo.save(u);
    }
    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        // inform the user about an authentication error
        if(beforeEnterEvent.getLocation()
                .getQueryParameters()
                .getParameters()
                .containsKey("error")) {
           // login.setError(true);
        }
    }
}
