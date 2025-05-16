package sa.gov.alriyadh.amana.mapper;

import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DateMapper {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    @Named("localDateToString")
    public String asString(LocalDateTime dateTime) {
        return dateTime != null ? dateTime.format(FORMATTER) : null;
    }

    @Named("stringToLocalDate")
    public LocalDateTime asLocalDate(String date) {
        return date != null ? LocalDateTime.parse(date, FORMATTER) : null;
    }
}