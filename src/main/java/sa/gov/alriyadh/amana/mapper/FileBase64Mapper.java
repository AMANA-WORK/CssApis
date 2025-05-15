package sa.gov.alriyadh.amana.mapper;

import org.mapstruct.Named;
import org.springframework.stereotype.Component;
import sa.gov.alriyadh.amana.utils.FileUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class FileBase64Mapper {

    @Named("base64ToPath")
    public String asFilePath(String base64) {
        String rootPath = "/img/CSS";
        return null;
    }

    @Named("filePathToBase64")
    public String asBase64String(String filePath) {
        return FileUtil.encodeFileToBase64Binary(filePath);
    }

}
