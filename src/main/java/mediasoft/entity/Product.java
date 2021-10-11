package mediasoft.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;

@Entity
@Getter
@Setter
public class Product {

    @Id
    @SequenceGenerator(name = "product_id_seq_generator",
            sequenceName = "product_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "product_id_seq_generator")
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private Set<ProductionOrder> productionOrders;

    public Product() {
    }

    public Product(String name) {
        this.name = name;
    }
}