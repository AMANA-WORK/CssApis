package sa.gov.alriyadh.amana.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "CSS_PARTICIPATION_TYPES", schema = "CSS")
public class CssParticipationType {
    @Id
    @Column(name = "TYPE_CODE", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer typeCode;

    @Size(max = 200)
    @NotNull
    @Column(name = "TYPE_NAME", nullable = false, length = 200)
    private String typeName;

}