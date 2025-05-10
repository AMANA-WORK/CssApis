package sa.gov.alriyadh.amana.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sa.gov.alriyadh.amana.entity.CssRequest;
import sa.gov.alriyadh.amana.entity.CssRequestPhase;
import sa.gov.alriyadh.amana.pojo.PhaseActionDetailView;
import sa.gov.alriyadh.amana.pojo.RequestPhase;
import sa.gov.alriyadh.amana.repository.CssPhaseActionRepository;
import sa.gov.alriyadh.amana.repository.CssRequestPhaseRepository;
import sa.gov.alriyadh.amana.repository.CssRequestRepository;
import sa.gov.alriyadh.amana.srinterface.IRequestService;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class RequestService implements IRequestService {

    @Autowired
    CssPhaseActionRepository cssPhaseActionRepository;
    @Autowired
    CssRequestPhaseRepository cssRequestPhaseRepository;
    @Autowired
    CssRequestRepository cssRequestRepository;

    @Override
    @Transactional
    public Map<String, Object> addRequestPhase(RequestPhase requestPhase) {
        Map<String, Object> output = new LinkedHashMap<>();
        PhaseActionDetailView actionDetail = cssPhaseActionRepository.getRoleActionDetail(requestPhase.getCurrentRole(), requestPhase.getActionId());
        Integer nextRequestPhaseSerial = cssRequestPhaseRepository.getNextSerial(requestPhase.getRequestNO());
        Optional<CssRequest> request = cssRequestRepository.findById(requestPhase.getRequestNO());
        if (request.isPresent()) {
            request.get().setRequestPhaseId(actionDetail.getToPhaseId());
            CssRequestPhase cssRequestPhase = new CssRequestPhase();
            cssRequestPhase.setRequestNo(requestPhase.getRequestNO());
            cssRequestPhase.setRequestPhaseSerial(nextRequestPhaseSerial);
            cssRequestPhase.setCreateDate(LocalDate.now());
            cssRequestPhase.setCreateUser(requestPhase.getIssueUser());
            cssRequestPhase.setFromPhaseId(actionDetail.getFromPhaseId());
            cssRequestPhase.setToPhaseId(actionDetail.getToPhaseId());
            cssRequestPhase.setFromRoleId(actionDetail.getFromRoleNo());
            cssRequestPhase.setToRoleId(actionDetail.getToRoleNo());
            cssRequestPhase.setNotes(requestPhase.getNotes());
            cssRequestPhaseRepository.save(cssRequestPhase);
            output.put("output", "Operation successful.");
            output.put("RequestNO", requestPhase.getRequestNO());
            output.put("RequestStatus", actionDetail.getToPhaseDesc());
        }else{
            output.put("output", "Operation fail (Request not found).");
            output.put("RequestNO", requestPhase.getRequestNO());
        }

        return output;
    }
}
