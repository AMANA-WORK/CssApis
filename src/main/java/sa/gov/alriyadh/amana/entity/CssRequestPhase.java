package sa.gov.alriyadh.amana.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "CSS_REQUEST_PHASES", schema = "CSS")
public class CssRequestPhase {
    @Id
    @Column(name = "REQUEST_PHASE_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CSS_REQUEST_PHASES_SEQ")
    @SequenceGenerator(name = "CSS_REQUEST_PHASES_SEQ", sequenceName = "CSS_REQUEST_PHASES_SEQ", allocationSize = 1,schema = "CSS")
    private Long requestPhaseId;

    @NotNull
    @Column(name = "REQUEST_NO", nullable = false)
    private Long requestNo;

    @Column(name = "REQUEST_PHASE_SERIAL")
    private Integer requestPhaseSerial;

    @Column(name = "FROM_ROLE_ID")
    private Integer fromRoleId;

    @Column(name = "TO_ROLE_ID")
    private Integer toRoleId;

    @NotNull
    @Column(name = "FROM_PHASE_ID", nullable = false)
    private Integer fromPhaseId;

    @NotNull
    @Column(name = "TO_PHASE_ID", nullable = false)
    private Integer toPhaseId;

    @Size(max = 4000)
    @Column(name = "NOTES", length = 4000)
    private String notes;

    @NotNull
    @Column(name = "CREATE_DATE", nullable = false)
    private LocalDate createDate;

    @Size(max = 15)
    @NotNull
    @Column(name = "CREATE_USER", nullable = false, length = 15)
    private String createUser;

}