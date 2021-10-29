package mediasoft.repository.specification;

import lombok.NoArgsConstructor;
import mediasoft.dto.worker.filter.WorkerFilterDto;
import mediasoft.entity.Role;
import mediasoft.entity.Worker;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
public final class WorkerSpecification {

    @SuppressWarnings("unchecked")
    public static Specification<Worker> findWorkers(Collection<WorkerFilterDto> filters) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new LinkedList<>();

            Fetch<Worker, Role> rolesFetch = root.fetch("roles", JoinType.LEFT);
            Join<Worker, Role> rolesJoin = (Join<Worker, Role>) rolesFetch;

            for (var filter : filters) {
                switch (filter.getWorkerField()) {
                    case ID -> {
                        Set<Integer> ids = filter.getValues().stream().map(Integer::parseInt).collect(Collectors.toSet());
                        predicates.add(criteriaBuilder.in(root.get("id")).value(ids));
                    }
                    case FAM -> predicates.add(criteriaBuilder.in(root.get("fam")).value(filter.getValues()));
                    case IM -> predicates.add(criteriaBuilder.in(root.get("im")).value(filter.getValues()));
                    case OTCH  -> predicates.add(criteriaBuilder.in(root.get("otch")).value(filter.getValues()));
                    case EMAIL -> predicates.add(criteriaBuilder.in(root.get("email")).value(filter.getValues()));

                    case ROLES -> {
                        predicates.add(criteriaBuilder.in(rolesJoin.get("code")).value(filter.getValues()));
                    }

                    default -> throw new IllegalArgumentException();
                }
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
