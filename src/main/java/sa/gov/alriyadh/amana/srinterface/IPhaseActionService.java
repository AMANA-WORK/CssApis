package sa.gov.alriyadh.amana.srinterface;

import org.springframework.data.repository.query.Param;
import sa.gov.alriyadh.amana.pojo.PhaseActionDetailView;
import sa.gov.alriyadh.amana.pojo.RoleActionView;

import java.util.List;

public interface IPhaseActionService {

    List<RoleActionView> getActions(Integer userRole);
    PhaseActionDetailView getRoleActionDetail(Integer userRole, Integer actionId);

}
