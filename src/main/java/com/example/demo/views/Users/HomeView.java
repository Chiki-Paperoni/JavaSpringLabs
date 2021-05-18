package com.example.demo.views.Users;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "/")
@PageTitle("Home")
public class HomeView extends Div {
    public HomeView() {
        add(new Anchor("/users","list of users"));
    }

}
