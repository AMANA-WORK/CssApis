package sa.gov.alriyadh.amana.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sa.gov.alriyadh.amana.entity.CssRequest;
import sa.gov.alriyadh.amana.pojo.CssRequestFilter;
import sa.gov.alriyadh.amana.pojo.RoleActionView;
import sa.gov.alriyadh.amana.srinterface.CssRequestRepositoryCustom;

import java.util.List;

public interface CssRequestRepository extends JpaRepository<CssRequest, Long>, CssRequestRepositoryCustom {

    @Query(value = "SELECT DIR_CODE,DIR_DESC FROM CMNV3.CMN_DIRECTORATES\n" +
            "WHERE VIEW_INDICATOR = :dirType  AND ACTIV = 1 ORDER BY 1", nativeQuery = true)
    List<Object> getDirectorates(@Param("dirType") Integer dirType);

    @Query(value = "SELECT COUNTRY_CODE, COUNTRY_NAME FROM CMNV3.CMN_COUNTRIES ORDER BY 1", nativeQuery = true)
    List<Object> getCountries();

    @Query(value = "SELECT TOWN_CODE, TOWN_NAME FROM CMNV3.CMN_TOWNS WHERE COUNTRY_CODE=:countryCode ORDER BY 1", nativeQuery = true)
    List<Object> getCities(@Param("countryCode") Integer countryCode);

    @Query(value = "SELECT phase_id, phase_desc FROM CSS.css_phases ORDER BY 1", nativeQuery = true)
    List<Object> getReqStatusList();

    List<CssRequest> findByRequestNo(Long requestNo);

}
