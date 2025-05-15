package sa.gov.alriyadh.amana.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import sa.gov.alriyadh.amana.entity.CssRequestAttachment;
import sa.gov.alriyadh.amana.entity.CssRole;
import sa.gov.alriyadh.amana.entity.pk.CssRequestAttachmentId;

import java.util.List;

public interface CssRequestAttachmentRepository extends JpaRepository<CssRequestAttachment, CssRequestAttachmentId> {

    @Query(value = "SELECT COALESCE(MAX(ATTACHE_SERIAL), 0) + 1 AS nextSerial " +
            "FROM CSS.CSS_REQUEST_ATTACHMENTS WHERE REQUEST_NO=:requestNo", nativeQuery = true)
    Integer getNextSerial(@Param("requestNo") long requestNo);

    List<CssRequestAttachment> findByIdRequestNo(Long requestNo);

}