package sa.gov.alriyadh.amana.srinterface;

import sa.gov.alriyadh.amana.entity.CssRequestAttachment;

import java.util.List;
import java.util.Map;

public interface IRequestAttachmentService {

    Map<String, Object> insertNewAttach(CssRequestAttachment cssAttachment);
    List<CssRequestAttachment> getRequestAttachments(Long requestNo);
}
