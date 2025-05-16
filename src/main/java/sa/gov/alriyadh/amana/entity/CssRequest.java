package sa.gov.alriyadh.amana.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "CSS_REQUESTS", schema = "CSS")
public class CssRequest {
    @Id
    @Column(name = "REQUEST_NO", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CSS_REQUESTS_SEQ")
    @SequenceGenerator(name = "CSS_REQUESTS_SEQ", sequenceName = "CSS_REQUESTS_SEQ", allocationSize = 1,schema = "CSS")
    private Long requestNo;

    //@NotNull
    @Column(name = "REQUEST_DATE", nullable = false)
    private LocalDateTime requestDate;

    //@NotNull
    @Column(name = "REQUEST_PHASE_ID", nullable = false)
    private Integer requestPhaseId;

    @Size(max = 20)
    @NotNull
    @Column(name = "EMPLOYEE_CODE", nullable = false, length = 20)
    private String employeeCode;

    @Size(max = 11)
    //@NotNull
    @Column(name = "MOBILE_NO", nullable = false, length = 11)
    private String mobileNo;

    @NotNull
    @Column(name = "PARTICIPATION_TYPE", nullable = false)
    private Integer participationType;

    @NotNull
    @Column(name = "EVENT_TYPE_CODE", nullable = false)
    private Integer eventTypeCode;

    @NotNull
    @Column(name = "EVENT_DATE", nullable = false)
    private LocalDateTime eventDate;

    @Size(max = 200)
    @Column(name = "SCIENTIFIC_PAPER", length = 200)
    private String scientificPaper;

    @Size(max = 200)
    @Column(name = "SESSION_NAME", length = 200)
    private String sessionName;

    @NotNull
    @Column(name = "EVENT_PLACE", nullable = false)
    private Integer eventPlace;

    @NotNull
    @Column(name = "DIR_TYPE", nullable = false)
    private Integer dirType;

    @NotNull
    @Column(name = "DIR_CODE", nullable = false)
    private Integer dirCode;

    @Column(name = "COUNTRY_CODE")
    private Integer countryCode;

    @Size(max = 200)
    @Column(name = "CITY_NAME")
    private String cityName;

    //@NotNull
    @Column(name = "PARTICIPATION_NUMBER", nullable = false)
    private Integer participationNumber;

    //@NotNull
    @Column(name = "COSTS_COVERED", nullable = false)
    private Integer costsCovered;

    @Formula("(SELECT p.phase_desc FROM CSS.css_phases p WHERE p.phase_id = request_phase_id)")
    private String requestStatus;

}