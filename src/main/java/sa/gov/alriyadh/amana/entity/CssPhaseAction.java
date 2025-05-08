package sa.gov.alriyadh.amana.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "CSS_PHASE_ACTIONS", schema = "CSS")
public class CssPhaseAction {
    @Id
    @Column(name = "PHASE_ACTION_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer phaseActionId;

    @NotNull
    @Column(name = "PARENT_PHASE_ID", nullable = false)
    private Integer parentPhaseId;

    @NotNull
    @Column(name = "CHILD_PHASE_ID", nullable = false)
    private Integer childPhaseId;

    @Column(name = "ACTIVE")
    private Integer active;

    @Column(name = "ACTION_ORDER")
    private Integer actionOrder;

    @Size(max = 4000)
    @Column(name = "NOTES", length = 4000)
    private String notes;

}