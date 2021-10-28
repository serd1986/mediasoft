package mediasoft.entity;

import lombok.Getter;
import lombok.Setter;
import mediasoft.entity.Position;
import mediasoft.entity.Role;
import mediasoft.entity.Skill;
import mediasoft.entity.StageWork;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.Collection;
import java.util.Set;

@Entity
@Getter
@Setter
@NamedEntityGraphs({
        @NamedEntityGraph(name = "Worker.roles", attributeNodes = {
                @NamedAttributeNode(value = "roles")
        })
})
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

    private String password;

    @OneToMany(mappedBy = "worker", fetch = FetchType.LAZY)
    private Set<Position> positions;

    @OneToMany(mappedBy = "worker", fetch = FetchType.LAZY)
    private Set<Skill> skills;

    @ManyToMany(mappedBy = "workers")
    private Set<StageWork> stageWorks;

    @ManyToMany
    @JoinTable(name = "worker_role",
            joinColumns = {@JoinColumn(name = "worker_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles;

    public Worker() {
    }

    public Worker(String fam, String im, String otch, String email, String password, Set<Role>roles) {
        this.fam = fam;
        this.im = im;
        this.otch = otch;
        this.email = email;
        this.password = password;
        this.roles = roles;

    }
}