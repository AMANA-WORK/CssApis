package sa.gov.alriyadh.amana.repository;

import org.springframework.stereotype.Repository;
import sa.gov.alriyadh.amana.entity.CssRequest;
import sa.gov.alriyadh.amana.pojo.CssRequestFilter;
import sa.gov.alriyadh.amana.srinterface.CssRequestRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CssRequestRepositoryImpl implements CssRequestRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CssRequest> findRequestsByFilter(CssRequestFilter filter) {
        StringBuilder sql = new StringBuilder("SELECT req.*, ph.NOTES AS requestNotes, (SELECT p.phase_desc FROM CSS.css_phases p WHERE p.phase_id = req.request_phase_id) as requestStatus" +
                " FROM CSS.css_requests req, CSS.css_request_phases ph ");
        sql.append("WHERE req.request_no = ph.request_no ");
        sql.append("AND req.request_phase_id = ph.to_phase_id ");

        Map<String, Object> params = new HashMap<>();

        if (filter.getRoleId() != null) {
            sql.append("AND (ph.to_role_id=:roleId) ");
            params.put("roleId", filter.getRoleId());
        }
        if (filter.getUserCode() != null) {
            sql.append("AND (ph.create_user=:userCode) ");
            params.put("userCode", filter.getUserCode());
        }
        if (filter.getRequestNo() != null) {
            sql.append("AND (req.request_no=:requestNo) ");
            params.put("requestNo", filter.getRequestNo());
        }
        if (filter.getStatusCode() != null) {
            sql.append("AND (req.REQUEST_PHASE_ID=:statusCode) ");
            params.put("statusCode", filter.getStatusCode());
        }
        if (filter.getDirCode() != null) {
            sql.append("AND (req.dir_code=:dirCode) ");
            params.put("dirCode", filter.getDirCode());
        }
        if (filter.getEventDate() != null) {
            sql.append("AND (req.event_date=TO_DATE(:eventDate, '`YYYY/MM/DD HH24:MI:SS')) ");
            params.put("eventDate", filter.getEventDate());
        }
        if (filter.getEventType() != null) {
            sql.append("AND (req.event_type_code=:eventType) ");
            params.put("eventType", filter.getEventType());
        }
        if (filter.getParticipationType() != null) {
            sql.append("AND (req.participation_type=:participationType) ");
            params.put("participationType", filter.getParticipationType());
        }
        if (filter.getEventPlace() != null) {
            sql.append("AND (req.event_place=:eventPlace) ");
            params.put("eventPlace", filter.getEventPlace());
        }
        if (filter.getCountryCode() != null) {
            sql.append("AND (req.country_code=:countryCode) ");
            params.put("countryCode", filter.getCountryCode());
        }
        if (filter.getCityCode() != null) {
            sql.append("AND (req.city_code=:cityName) ");
            params.put("cityName", filter.getCityCode());
        }

        sql.append("ORDER BY req.request_no");

        System.out.println("SQL query: " + sql.toString() + "   " + filter.getUserCode());

        Query query = entityManager.createNativeQuery(sql.toString(), CssRequest.class);
        params.forEach(query::setParameter);


        return query.getResultList();
    }
}
