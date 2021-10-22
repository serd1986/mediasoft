package mediasoft.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.Collection;
import java.util.Set;

@Entity
@Getter
@Setter
public class Worker {

    @Id
    @SequenceGenerator(name = "worker_id_seq_generator",
            sequenceName = "worker_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "worker_id_seq_generator")
    private Integer id;

    private String fam;
    private String im;
    private String otch;
    private String email;

    @OneToMany(mappedBy = "worker", fetch = FetchType.EAGER)
    private Set<Position> positions;

    @OneToMany(mappedBy = "worker", fetch = FetchType.EAGER)
    private Set<Skill> skills;

    @ManyToMany(mappedBy = "workers")
    private Collection<StageWork> stageWorks;

    public Worker() {
    }

    public Worker(String fam, String im, String otch) {
        this.fam = fam;
        this.im = im;
        this.otch = otch;
    }
}