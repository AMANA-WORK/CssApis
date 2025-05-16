package sa.gov.alriyadh.amana.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sa.gov.alriyadh.amana.entity.CssRequest;
import sa.gov.alriyadh.amana.pojo.RoleActionView;

import java.util.List;

public interface CssRequestRepository extends JpaRepository<CssRequest, Long> {

    @Query(value = "SELECT DIR_CODE,DIR_DESC FROM CMNV3.CMN_DIRECTORATES\n" +
            "WHERE VIEW_INDICATOR = :dirType  AND ACTIV = 1 ORDER BY 1", nativeQuery = true)
    List<Object[]> getDirectorates(@Param("dirType") Integer dirType);

    @Query(value = "SELECT COUNTRY_CODE, COUNTRY_NAME FROM CMNV3.CMN_COUNTRIES ORDER BY 1", nativeQuery = true)
    List<Object[]> getCountries();

    List<CssRequest> findByRequestNo(Long requestNo);

}
