package com.example.demo.views.Users;

import com.example.demo.model.OrderService;
import com.example.demo.model.UserService;
import com.example.demo.repos.OrderEntity;
import com.example.demo.repos.OrderRepo;
import com.helger.commons.annotation.LazilyInitialized;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import domain.order.Order;
import domain.user.DUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@Route(value = "/orders")
@PageTitle("Oredes")
public class OrderView extends FormLayout implements BeforeEnterObserver {
    @Autowired
    private OrderService orderService = new OrderService();
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String currentPrincipalName = authentication.getName();

    private Binder<Order> binder = new Binder<>(Order.class);
    private Image img1 = new Image("https://content1.rozetka.com.ua/goods/images/big/40159927.jpg","alt");
    private Image img2 = new Image("https://content1.rozetka.com.ua/goods/images/big/10756753.jpg","alt");
    private Image img3 = new Image("https://encrypted-tbn2.gstatic.com/shopping?q=tbn:ANd9GcQ8Yem2INRpDPfRI4KKxRDZhEJdrpyh2QoMoEasU91PT5n649qk5Tc8ZwT1myiN7yV-HTPeuikCZA&usqp=CAc","alt");
    private Image img4 = new Image("https://cdn.comfy.ua/media/catalog/product/cache/5/image/1440x1080/62defc7f46f3fbfc8afcd112227d1181/2/8/2802262-2.jpg","alt");
    private Image img5 = new Image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRCJft7UzMoDO2aFZ3Drp9le3z-tB2gw-H6Ik23dNFHqVsubETFj2tI3NDL2w&usqp=CAc","alt");
    private Image img6 = new Image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTD_K68ZAhaxV-zb-T3QFYhj7wcnJ_VZk6FJFWNRPFzwVezG0zM_niRUqhfatdJrxADHM7b-u0&usqp=CAc","alt");
    private Div div1 = new Div();

    @Autowired
    private OrderRepo orderRepo;

    //private TextField type = new TextField("Which plates do you need");
    private Select<String> material = new Select<>("gold","silver","glass","ceramics","fiance","porcelain");
    private NumberField amount = new NumberField("amount");
    private Select<String> type = new Select<>("SauceBowl","Plate","SugarBowl","TeaPot");
    VerticalLayout v = new VerticalLayout();


    private Button save = new Button("Confirm");
    private Button myOrders = new Button("See my orders");

    OrderView() {
        HorizontalLayout buttons = new HorizontalLayout(save,myOrders);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        type.setLabel("Which plates do you need");
        material.setLabel("Which material do you need");
        add(type,material,amount ,buttons);
        save.addClickListener(event -> save());
        myOrders.addClickListener(event -> ordersList());
        binder.bindInstanceFields(this);
        binder.setBean(new Order());

//        List<Order> orders = orderService.getUserOrders(currentPrincipalName);
//        add(new Paragraph("YOUR ORDERS"));
//        for (Order order:orders) {
//            add(new Paragraph("Type: " + order.getType() + " Material: " + order.getMaterial() + " Amount: " + order.getAmount()));
//        }
        img1.setHeight("200px");
        img2.setHeight("200px");
        img3.setHeight("200px");
        img4.setHeight("200px");
        img5.setHeight("200px");
        img6.setHeight("200px");

        div1.add(img1,img2,img3,img4,img5,img6);
        add(div1);
        add(v);
    }
    private void save() {
        OrderEntity o = new OrderEntity();
        o.setAmount(amount.getValue());
        o.setMaterial(material.getValue());
        o.setType(type.getValue());
        o.setUsername(currentPrincipalName);
        orderRepo.save(o);
        System.out.println(orderRepo.findByUsername("Eugene"));
    }
    private void ordersList() {
        v.removeAll();
        Iterable<OrderEntity> orders = orderRepo.findAll();
        for (OrderEntity o : orders) {
            v.add(new Paragraph(o.getId() + ")Type: " + o.getType() + " Material: " + o.getMaterial() + " Amount: " + o.getAmount()));
        }
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
