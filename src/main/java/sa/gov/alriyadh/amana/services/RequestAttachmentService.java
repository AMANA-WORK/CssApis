package sa.gov.alriyadh.amana.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sa.gov.alriyadh.amana.entity.CssRequestAttachment;
import sa.gov.alriyadh.amana.repository.CssRequestAttachmentRepository;
import sa.gov.alriyadh.amana.srinterface.IRequestAttachmentService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class RequestAttachmentService implements IRequestAttachmentService {

    @Autowired
    CssRequestAttachmentRepository attachmentRepository;

    @Override
    public Map<String, Object> insertNewAttach(CssRequestAttachment cssAttachment) {
        Map<String, Object> output = new LinkedHashMap<>();
        Integer nextSerial = attachmentRepository.getNextSerial(cssAttachment.getId().getRequestNo());
        attachmentRepository.save(cssAttachment);
        output.put("RequestNO", cssAttachment.getId().getRequestNo());
        output.put("AttachSerial", nextSerial);
        output.put("output", "Operation successful.");
        return output;
    }

    @Override
    public List<CssRequestAttachment> getRequestAttachments(Long RequestNo) {
        return attachmentRepository.findByIdRequestNo(RequestNo);
    }
}
