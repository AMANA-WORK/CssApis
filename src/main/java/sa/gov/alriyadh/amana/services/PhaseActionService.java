package sa.gov.alriyadh.amana.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sa.gov.alriyadh.amana.pojo.PhaseActionDetailView;
import sa.gov.alriyadh.amana.pojo.RoleActionView;
import sa.gov.alriyadh.amana.repository.CssPhaseActionRepository;
import sa.gov.alriyadh.amana.srinterface.IPhaseActionService;

import java.util.List;

@Service
public class PhaseActionService implements IPhaseActionService {

    @Autowired
    CssPhaseActionRepository actionRepository;

    @Override
    public List<RoleActionView[]> getActions(Integer userRole) {
        return actionRepository.getUserRoleActions(userRole);
    }

    @Override
    public PhaseActionDetailView getRoleActionDetail(Integer userRole, Integer actionId) {
        return actionRepository.getRoleActionDetail(userRole, actionId);
    }

}
