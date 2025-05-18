package sa.gov.alriyadh.amana.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link sa.gov.alriyadh.amana.entity.CssRequestPhase}
 */
@Value
public class CssRequestPhaseDto implements Serializable {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long requestPhaseId;

    @NotNull
    private Long requestNo;

    private Integer requestPhaseSerial;

    private Integer fromRoleId;

    private Integer toRoleId;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer fromPhaseId;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer toPhaseId;

    @Size(max = 4000)
    private String notes;

    @NotNull
    private String createDate; // As String in the format yyyy/MM/dd HH:mm:ss

    @NotNull
    @Size(max = 15)
    private String createUser;
}