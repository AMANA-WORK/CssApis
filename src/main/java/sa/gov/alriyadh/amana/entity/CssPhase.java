package sa.gov.alriyadh.amana.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "CSS_PHASES", schema = "CSS")
public class CssPhase {
    @Id
    @Column(name = "PHASE_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer phaseId;

    @Size(max = 500)
    @NotNull
    @Column(name = "PHASE_NAME", nullable = false, length = 500)
    private String phaseName;

    @Size(max = 500)
    @Column(name = "PHASE_DESC", length = 500)
    private String phaseDesc;

    @NotNull
    @Column(name = "FROM_ROLE_NO", nullable = false)
    private Integer fromRoleNo;

    @NotNull
    @Column(name = "TO_ROLE_NO", nullable = false)
    private Integer toRoleNo;

    @Size(max = 2000)
    @Column(name = "BENEFICIARY_MESSAGE", length = 2000)
    private String beneficiaryMessage;

    @Column(name = "ACTIVE")
    private Integer active;

    @Size(max = 200)
    @Column(name = "NOTE", length = 200)
    private String note;

}