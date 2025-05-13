package sa.gov.alriyadh.amana.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import sa.gov.alriyadh.amana.dto.CssRequestDto;
import sa.gov.alriyadh.amana.entity.CssRequest;

@Mapper(componentModel = "spring", uses = {DateMapper.class})
public interface CssRequestMapper {

    CssRequestMapper INSTANCE = Mappers.getMapper(CssRequestMapper.class);

    @Mappings({
            @Mapping(source = "eventDate", target = "eventDate", qualifiedByName = "localDateToString"),
            @Mapping(source = "requestDate", target = "requestDate", qualifiedByName = "localDateToString")
    })
    CssRequestDto toDto(CssRequest entity);

    @Mappings({
            @Mapping(source = "eventDate", target = "eventDate", qualifiedByName = "stringToLocalDate"),
            @Mapping(target = "requestDate", expression = "java(java.time.LocalDate.now())")
    })
    CssRequest toEntity(CssRequestDto dto);
}