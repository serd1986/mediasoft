package mediasoft.entity;


import lombok.Getter;
import lombok.Setter;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
@Getter
@Setter
public class Role {

@Id
private Integer id;
private String code;

@ManyToMany(mappedBy = "roles")
private Set<Worker> workerSet;

    public Role(String code) {
        this.code = code;
    }
}
