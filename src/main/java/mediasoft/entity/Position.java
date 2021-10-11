package mediasoft.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Getter
@Setter
public class Position {

    @Id
    @SequenceGenerator(name = "position_id_seq_generator",
            sequenceName = "position_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "position_id_seq_generator")
    private Integer id;
    private Instant dtIn;
    private String name;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "idWorker")
    private Worker worker;

    public Position() {
    }

    public Position(Instant dtIn, String name) {
        this.dtIn = dtIn;
        this.name = name;
    }
}