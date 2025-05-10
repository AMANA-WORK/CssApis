package sa.gov.alriyadh.amana.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sa.gov.alriyadh.amana.entity.CssParticipationType;
import sa.gov.alriyadh.amana.repository.CssParticipationTypeRepository;
import sa.gov.alriyadh.amana.srinterface.IParticipationTypeService;

import java.util.List;

@Service
public class ParticipationTypeService implements IParticipationTypeService {

    @Autowired
    CssParticipationTypeRepository participationTypeRepository;

    @Override
    public List<CssParticipationType> getAllParticipationTypes() {
        return participationTypeRepository.findAll();
    }
}
