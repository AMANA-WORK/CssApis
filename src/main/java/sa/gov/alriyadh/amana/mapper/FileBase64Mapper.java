package sa.gov.alriyadh.amana.mapper;

import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class FileBase64Mapper {

    @Named("base64ToPath")
    public String asFilePath(String base64) {
        return null;
    }

    @Named("filePathToBase64")
    public LocalDate asBase64String(String filePath) {
        return null;
    }

}
