package sa.gov.alriyadh.amana.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CssRequestAttachmentDto {
    private Long requestNo;
    private Integer attacheSerial;
    private String fileDesc;
    private String fileBase64; // <- contains file content, not path!
}
