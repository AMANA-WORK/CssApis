package sa.gov.alriyadh.amana.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sa.gov.alriyadh.amana.entity.CssEventType;
import sa.gov.alriyadh.amana.repository.CssEventTypeRepository;
import sa.gov.alriyadh.amana.srinterface.IEventTypeService;

import java.util.List;

@Service
public class EventTypeService implements IEventTypeService {

    @Autowired
    CssEventTypeRepository cssEventTypeRepository;

    @Override
    public List<CssEventType> getEventTypes() {
        return cssEventTypeRepository.findAll();
    }
}
