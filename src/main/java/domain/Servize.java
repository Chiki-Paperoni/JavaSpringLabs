package domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Servize {
    private long id;
    private List<Dishes> dishes;
}
