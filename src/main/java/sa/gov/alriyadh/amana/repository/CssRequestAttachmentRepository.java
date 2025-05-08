package sa.gov.alriyadh.amana.repository;

import org.springframework.data.repository.CrudRepository;
import sa.gov.alriyadh.amana.entity.CssRequestAttachment;
import sa.gov.alriyadh.amana.entity.pk.CssRequestAttachmentId;

public interface CssRequestAttachmentRepository extends CrudRepository<CssRequestAttachment, CssRequestAttachmentId> {
}