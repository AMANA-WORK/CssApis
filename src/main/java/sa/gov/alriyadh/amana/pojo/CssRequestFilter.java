package sa.gov.alriyadh.amana.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sa.gov.alriyadh.amana.validation.AtLeastOneField;

@AtLeastOneField
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CssRequestFilter {

    private Integer roleId;
    private String userCode;
    private Long requestNo;
    private Integer statusCode;
    private Integer dirCode;
    @JsonFormat(pattern = "yyyy/MM/dd HH24:mm:ss")
    private String eventDate; // Or LocalDateTime
    private Integer eventType;
    private Integer participationType;
    private Integer eventPlace;
    private Integer countryCode;
    private String cityCode;

}
