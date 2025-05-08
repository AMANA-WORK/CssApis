package sa.gov.alriyadh.amana.entity;

import lombok.Getter;
import lombok.Setter;
import sa.gov.alriyadh.amana.entity.pk.CssRequestAttachmentId;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "CSS_REQUEST_ATTACHMENTS", schema = "CSS")
public class CssRequestAttachment {
    @EmbeddedId
    private CssRequestAttachmentId id;

    @Size(max = 500)
    @NotNull
    @Column(name = "FILE_PATH", nullable = false, length = 500)
    private String filePath;

    @Size(max = 200)
    @Column(name = "FILE_DESC", length = 200)
    private String fileDesc;

}