package sa.gov.alriyadh.amana.entity.pk;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class CssRequestAttachmentId implements Serializable {
    private static final long serialVersionUID = -9132004621902457029L;
    @NotNull
    @Column(name = "REQUEST_NO", nullable = false)
    private Long requestNo;

    @NotNull
    @Column(name = "ATTACHE_SERIAL", nullable = false)
    private Short attacheSerial;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CssRequestAttachmentId entity = (CssRequestAttachmentId) o;
        return Objects.equals(this.attacheSerial, entity.attacheSerial) &&
                Objects.equals(this.requestNo, entity.requestNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attacheSerial, requestNo);
    }

}