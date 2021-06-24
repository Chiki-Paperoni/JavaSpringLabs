package domain.order;

import domain.Servize;
import domain.user.DUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@AllArgsConstructor
@Component
public class Order {

    private Long id;
    private DUser DUser;
    private List<Servize> servize;
    private String username;
    private String type;
    private double amount;
    private String material;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getMaterial() {
        return material;
    }

    private double total;

    public Order(DUser DUser, List<Servize> servize) {
        this.DUser = DUser;
        this.servize = servize;
    }
    public Order(){}
}