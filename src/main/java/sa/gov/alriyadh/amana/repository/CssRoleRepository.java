package sa.gov.alriyadh.amana.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import sa.gov.alriyadh.amana.entity.CssRole;

@RepositoryRestResource(path = "roles")
public interface CssRoleRepository extends CrudRepository<CssRole, Integer> {
}