package sa.gov.alriyadh.amana.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import sa.gov.alriyadh.amana.entity.CssRequestAttachment;
import sa.gov.alriyadh.amana.entity.dto.CssRequestAttachmentDto;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CssRequestDto {

    private Long requestNo;
    private String requestDate;
    private Integer requestPhaseId;

    @NotNull(message = "EmployeeCode is required")
    private String employeeCode;

    private String mobileNo;

    @NotNull(message = "ParticipationType is required.")
    private Integer participationType;
    @NotNull(message = "EventTypeCode is required.")
    private Integer eventTypeCode;
    @NotNull
    @NotNull(message = "EventDate is required.")
    private String eventDate; // As String in the format yyyy/MM/dd HH:mm:ss
    private String scientificPaper;
    private String sessionName;
    @NotNull(message = "Must enter EventPlace 1 for internal or 2 for out of Amana.")
    private Integer eventPlace;
    @NotNull@NotNull(message = "DirType is required.")
    private Integer dirType;
    @NotNull@NotNull(message = "DirCode is required.")
    private Integer dirCode;
    private Integer countryCode;
    private String cityName;
    private Integer participationNumber;
    private Integer costsCovered;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<CssRequestAttachmentDto> attachments;
}
