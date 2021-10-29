package mediasoft.entity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.Collection;

@Entity
@Getter
@Setter
public class StageWork {

    @Id
    @SequenceGenerator(name = "stage_work_id_seq_generator",
            sequenceName = "stage_work_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "stage_work_id_seq_generator")
    private Integer id;
    private String type;
    private Instant dtEnd;

    @ManyToMany(mappedBy = "stageWorks")
    private Collection<ProductionOrder> productionOrders;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "worker_stage_work",
            joinColumns = @JoinColumn(name = "id_worker"),
            inverseJoinColumns = @JoinColumn(name = "id_stage_work")
    )
    private Collection<Worker> workers;


    public StageWork() {
    }

    public StageWork(String type, Instant dtEnd) {
        this.type = type;
        this.dtEnd = dtEnd;
    }
}

