package domain.order;

import domain.Servize;
import domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Order {

    private Long id;
    private User user;
    private List<Servize> servize;
    private double total;

    public Order(User user, List<Servize> servize) {
        this.user = user;
        this.servize = servize;
    }
}