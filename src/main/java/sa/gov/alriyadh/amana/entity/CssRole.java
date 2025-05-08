package sa.gov.alriyadh.amana.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "CSS_ROLES", schema = "CSS")
public class CssRole {
    @Id
    @Column(name = "ROLE_NO", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer roleNo;

    @Size(max = 200)
    @NotNull
    @Column(name = "ROLE_DESC", nullable = false, length = 200)
    private String roleDesc;

    @NotNull
    @Column(name = "STATUS", nullable = false)
    private Integer status;

}