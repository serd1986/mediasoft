package mediasoft.repository;

import mediasoft.entity.Worker;
import mediasoft.entity.projection.WorkerIdProjection;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
    public interface WorkerRepository  extends JpaRepository<Worker, Integer>, JpaSpecificationExecutor<Worker> {

    Optional<Worker> findByIm(String im);

    Optional<Worker> findByEmail(String email);

    WorkerIdProjection findOneByEmail(String email);

    @EntityGraph("Worker.roles")
    Optional<Worker> findOneWithRolesByEmail(String email);

    @EntityGraph("Worker.roles")
    @Query("select u from Worker u")
    List<Worker> findAllWithRoles();
}

