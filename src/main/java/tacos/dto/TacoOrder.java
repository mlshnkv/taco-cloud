package tacos.dto;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Column;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class TacoOrder implements Serializable {

    private Long id;
    private LocalDate placedAt;

    @Column("customer_name")
    @NotBlank(message="Delivery name is required")
    private String deliveryName;
    private String deliveryStreet;
    private String deliveryCity;
    private String deliveryState;
    private String deliveryZip;
    private String ccNumber;
    private String ccExpiration;
    private String ccCVV;
    private List<Taco> tacos = new ArrayList<>();

    public void addTaco(Taco taco) {
        this.tacos.add(taco);
    }
}