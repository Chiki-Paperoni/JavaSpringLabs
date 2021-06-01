package domain.order;

import domain.Servize;
import domain.user.DUser;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Order {

    private Long id;
    private DUser DUser;
    private List<Servize> servize;
    private double total;

    public Order(DUser DUser, List<Servize> servize) {
        this.DUser = DUser;
        this.servize = servize;
    }
}