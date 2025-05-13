package sa.gov.alriyadh.amana.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CssRequestDto {

    private Long requestNo;
    private String requestDate;
    private Integer requestPhaseId;

    @NotNull(message = "EmployeeCode is required")
    private String employeeCode;

    private String mobileNo;

    @NotNull(message = "ParticipationType is required")
    private Integer participationType;
    @NotNull(message = "EventTypeCode is required")
    private Integer eventTypeCode;
    @NotNull
    private String eventDate; // As String in the format yyyy/MM/dd HH:mm:ss
    private String scientificPaper;
    private String sessionName;
    private Integer eventPlace;
    @NotNull@NotNull(message = "DirType is required")
    private Integer dirType;
    @NotNull@NotNull(message = "DirCode is required")
    private Integer dirCode;
    private Integer countryCode;
    private String cityName;
    private Integer participationNumber;
    private Integer costsCovered;
}
