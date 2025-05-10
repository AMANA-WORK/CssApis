package sa.gov.alriyadh.amana.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import sa.gov.alriyadh.amana.entity.CssParticipationType;

public interface CssParticipationTypeRepository extends JpaRepository<CssParticipationType, Integer> {
}