package sa.gov.alriyadh.amana.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sa.gov.alriyadh.amana.entity.CssRequest;

public interface CssRequestRepository extends JpaRepository<CssRequest, Long> {
}
