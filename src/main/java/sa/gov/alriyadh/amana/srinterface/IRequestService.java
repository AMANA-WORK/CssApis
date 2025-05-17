package sa.gov.alriyadh.amana.srinterface;

import sa.gov.alriyadh.amana.dto.CssRequestDto;
import sa.gov.alriyadh.amana.entity.CssRequest;
import sa.gov.alriyadh.amana.pojo.CssRequestFilter;
import sa.gov.alriyadh.amana.pojo.RequestPhase;

import java.util.List;
import java.util.Map;

public interface IRequestService {

    List<Object[]> getDirectorates(Integer dirType);
    List<Object[]> getCountries();
    List<Object[]> getReqStatusList();
    Map<String, Object> addNewRequest(CssRequestDto cssRequestDto);
    Map<String, Object> addRequestPhase(RequestPhase requestPhase);
    List<CssRequestDto> findRequestsByFilter(CssRequestFilter filter);

}
