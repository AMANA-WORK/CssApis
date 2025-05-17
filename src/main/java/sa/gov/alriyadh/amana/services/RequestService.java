package sa.gov.alriyadh.amana.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sa.gov.alriyadh.amana.dto.CssRequestDto;
import sa.gov.alriyadh.amana.entity.CssRequest;
import sa.gov.alriyadh.amana.entity.CssRequestPhase;
import sa.gov.alriyadh.amana.entity.dto.CssRequestAttachmentDto;
import sa.gov.alriyadh.amana.mapper.CssRequestMapper;
import sa.gov.alriyadh.amana.pojo.CssRequestFilter;
import sa.gov.alriyadh.amana.pojo.PhaseActionDetailView;
import sa.gov.alriyadh.amana.pojo.RequestPhase;
import sa.gov.alriyadh.amana.repository.CssPhaseActionRepository;
import sa.gov.alriyadh.amana.repository.CssRequestPhaseRepository;
import sa.gov.alriyadh.amana.repository.CssRequestRepository;
import sa.gov.alriyadh.amana.srinterface.IRequestService;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RequestService implements IRequestService {

    @Autowired
    CssPhaseActionRepository cssPhaseActionRepository;
    @Autowired
    CssRequestPhaseRepository cssRequestPhaseRepository;
    @Autowired
    CssRequestRepository cssRequestRepository;
    @Autowired
    RequestAttachmentService requestAttachmentService;
    @Autowired
    CssRequestMapper mapper;

    @Override
    public List<Object[]> getDirectorates(Integer dirType) {
        return cssRequestRepository.getDirectorates(dirType);
    }

    @Override
    public List<Object[]> getCountries() {
        return cssRequestRepository.getCountries();
    }

    @Override
    public List<Object[]> getReqStatusList() {
        return cssRequestRepository.getReqStatusList();
    }

    @Override
    public Map<String, Object> addNewRequest(CssRequestDto cssRequestDto) {
        Map<String, Object> output = new LinkedHashMap<>();
        CssRequest entity = mapper.toEntity(cssRequestDto); // sets requestDate = now
        entity.setRequestPhaseId(1);
        CssRequest request = cssRequestRepository.save(entity);
        CssRequestDto savedRequestDto = mapper.toDto(request); // return full DTO with generated requestNo and formatted dates
        PhaseActionDetailView actionDetail = cssPhaseActionRepository.getRoleActionDetail(1, 1);
        Integer nextRequestPhaseSerial = cssRequestPhaseRepository.getNextSerial(savedRequestDto.getRequestNo());

        CssRequestPhase cssRequestPhase = new CssRequestPhase();
        cssRequestPhase.setRequestNo(savedRequestDto.getRequestNo());
        cssRequestPhase.setRequestPhaseSerial(nextRequestPhaseSerial);
        cssRequestPhase.setCreateDate(LocalDate.now());
        cssRequestPhase.setCreateUser(savedRequestDto.getEmployeeCode());
        cssRequestPhase.setFromPhaseId(actionDetail.getFromPhaseId());
        cssRequestPhase.setToPhaseId(actionDetail.getToPhaseId());
        cssRequestPhase.setFromRoleId(actionDetail.getFromRoleNo());
        cssRequestPhase.setToRoleId(actionDetail.getToRoleNo());
        cssRequestPhase.setNotes(actionDetail.getFromPhaseDesc());
        //Save request entity edits (requestPhaseId).
        cssRequestRepository.save(request);
        //Save the new cssRequestPhase entity.
        cssRequestPhaseRepository.save(cssRequestPhase);
        //Save request attachments if exist in cssRequestDto.
        if(cssRequestDto.getAttachments() != null && !cssRequestDto.getAttachments().isEmpty()) {
            for(CssRequestAttachmentDto attachmentDto : cssRequestDto.getAttachments() ) {
                attachmentDto.setRequestNo(request.getRequestNo());
                requestAttachmentService.insertNewAttach(attachmentDto);
            }
        }

        output.put("output", "Operation successful.");
        output.put("RequestNO", savedRequestDto.getRequestNo());
        output.put("RequestStatus", actionDetail.getToPhaseDesc());

        return output;
    }


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
            cssRequestRepository.save(request.get());
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

    @Override
    public List<CssRequestDto> findRequestsByFilter(CssRequestFilter filter) {
        return cssRequestRepository.findRequestsByFilter(filter).stream()
                .map(cssRequest -> mapper.toDto(cssRequest))
                .collect(Collectors.toList());
    }

//    @Override
//    public List<CssRequestDto> findByRequestNo(Long requestNo) {
//        return cssRequestRepository.findByRequestNo(requestNo).stream()
//                .map(cssRequest -> mapper.toDto(cssRequest))
//                .collect(Collectors.toList());
//    }
}
