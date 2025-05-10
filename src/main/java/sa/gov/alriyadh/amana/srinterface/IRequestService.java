package sa.gov.alriyadh.amana.srinterface;

import sa.gov.alriyadh.amana.pojo.RequestPhase;

import java.util.Map;

public interface IRequestService {

    Map<String, Object> addRequestPhase(RequestPhase requestPhase);

}
