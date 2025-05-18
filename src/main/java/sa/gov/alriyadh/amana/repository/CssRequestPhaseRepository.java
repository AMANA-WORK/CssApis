package sa.gov.alriyadh.amana.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sa.gov.alriyadh.amana.entity.CssRequestPhase;

import java.util.List;

public interface CssRequestPhaseRepository extends JpaRepository<CssRequestPhase, Long>{

    @Query(value = "SELECT COALESCE(MAX(request_phase_serial), 0) + 1 AS nextSerial " +
            "FROM CSS.css_request_phases WHERE request_no=:requestNo", nativeQuery = true)
    Integer getNextSerial(@Param("requestNo") long requestNo);

    List<CssRequestPhase> findByRequestNo(long requestNo);
}
