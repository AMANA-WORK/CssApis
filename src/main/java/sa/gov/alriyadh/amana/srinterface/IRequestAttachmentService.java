package sa.gov.alriyadh.amana.srinterface;

import sa.gov.alriyadh.amana.entity.CssRequestAttachment;
import sa.gov.alriyadh.amana.entity.dto.CssRequestAttachmentDto;

import java.util.List;
import java.util.Map;

public interface IRequestAttachmentService {

    Map<String, Object> insertNewAttach(CssRequestAttachmentDto cssRequestAttachmentDto);
    List<CssRequestAttachmentDto> getRequestAttachments(Long requestNo);
}
