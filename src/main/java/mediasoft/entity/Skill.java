package mediasoft.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Skill {

    @Id
    @SequenceGenerator(name = "skill_id_seq_generator",
            sequenceName = "skill_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "skill_id_seq_generator")
    private Integer id;
    private String name;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "idWorker")
    private Worker worker;

    public Skill() {
    }

    public Skill(String name) {
        this.name = name;
    }
}