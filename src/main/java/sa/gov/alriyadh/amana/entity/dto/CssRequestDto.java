package sa.gov.alriyadh.amana.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CssRequestDto implements Serializable {
    private Long requestNo;
    @NotNull
    private LocalDate requestDate;
    @NotNull
    private Integer requestPhaseId;
    @NotNull(message = "{EMPLOYEE_NO_REQUIRED}")
    private String employeeCode;
    @NotNull
    @Size(max = 11, message = "Mobile no must be 11 number")
    private String mobileNo;
    @NotNull
    private Integer participationType;
    @NotNull
    private Integer eventTypeCode;
    @NotNull
    private LocalDate eventDate;
    private String scientificPaper;
    private String sessionName;
    @NotNull
    private Integer eventPlace;
    @NotNull
    private Integer dirType;
    @NotNull
    private Integer dirCode;
    private Integer countryCode;
    private Integer cityCode;
    @NotNull
    private Integer participationNumber;
    @NotNull
    private Integer costsCovered;
}