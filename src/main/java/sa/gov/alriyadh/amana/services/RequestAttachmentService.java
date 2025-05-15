package sa.gov.alriyadh.amana.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sa.gov.alriyadh.amana.entity.CssRequestAttachment;
import sa.gov.alriyadh.amana.entity.dto.CssRequestAttachmentDto;
import sa.gov.alriyadh.amana.entity.pk.CssRequestAttachmentId;
import sa.gov.alriyadh.amana.mapper.CssRequestMapper;
import sa.gov.alriyadh.amana.repository.CssRequestAttachmentRepository;
import sa.gov.alriyadh.amana.srinterface.IRequestAttachmentService;
import sa.gov.alriyadh.amana.utils.FileUtil;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RequestAttachmentService implements IRequestAttachmentService {

    @Autowired
    CssRequestAttachmentRepository attachmentRepository;

    @Autowired
    CssRequestMapper mapper;

    @Override
    public Map<String, Object> insertNewAttach(CssRequestAttachmentDto cssRequestAttachmentDto) {
        Map<String, Object> output = new LinkedHashMap<>();
        Integer nextSerial = attachmentRepository.getNextSerial(cssRequestAttachmentDto.getRequestNo());
        String fileName = cssRequestAttachmentDto.getRequestNo() + "_"
                + String.format("%02d", nextSerial) + "." + FileUtil.getFileBase64Extention(cssRequestAttachmentDto.getFileBase64());
        System.out.println(fileName);
        String filePath = "C:"+FileUtil.getApiFileRoot() + fileName; // local
        //String filePath = FileUtil.getApiFileRoot() + fileName; // server
        FileUtil.createFile(filePath, cssRequestAttachmentDto.getFileBase64());
        CssRequestAttachment cssAttachment = mapper.toEntity(cssRequestAttachmentDto);
        CssRequestAttachmentId attachmentId =
                new CssRequestAttachmentId(cssRequestAttachmentDto.getRequestNo(), nextSerial);
        cssAttachment.setId(attachmentId);
        cssAttachment.setFilePath(filePath);
        attachmentRepository.save(cssAttachment);
        output.put("RequestNO", cssAttachment.getId().getRequestNo());
        output.put("AttachSerial", nextSerial);
        output.put("output", "Operation successful.");
        return output;
    }

    @Override
    public List<CssRequestAttachmentDto> getRequestAttachments(Long requestNo) {
        return attachmentRepository.findByIdRequestNo(requestNo).stream()
                .map(cssRequestAttachment -> mapper.toDto(cssRequestAttachment))
                .collect(Collectors.toList());
    }
}
