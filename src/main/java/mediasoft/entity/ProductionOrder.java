package mediasoft.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.Collection;

@Entity
@Getter
@Setter
public class ProductionOrder {

    @Id
    @SequenceGenerator(name = "production_order_id_seq_generator",
            sequenceName = "production_order_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "production_order_id_seq_generator")
    private Integer id;
    private Instant dtOrder;
    private Instant dtOrderEnd;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "idProduct")
    private Product product;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "product_order_stage_work",
            joinColumns = @JoinColumn(name = "id_production_order"),
            inverseJoinColumns = @JoinColumn(name = "id_stage_work")
    )
    private Collection<StageWork> stageWorks;

    public ProductionOrder() {
    }

    public ProductionOrder(Instant dtOrder, Instant dtOrderEnd) {
        this.dtOrder = dtOrder;
        this.dtOrderEnd = dtOrderEnd;
    }
}