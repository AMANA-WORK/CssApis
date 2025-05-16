package sa.gov.alriyadh.amana.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import sa.gov.alriyadh.amana.dto.CssRequestDto;
import sa.gov.alriyadh.amana.entity.CssRequest;
import sa.gov.alriyadh.amana.entity.CssRequestAttachment;
import sa.gov.alriyadh.amana.entity.dto.CssRequestAttachmentDto;

@Mapper(componentModel = "spring", uses = {DateMapper.class, FileBase64Mapper.class})
public interface CssRequestMapper {

    CssRequestMapper INSTANCE = Mappers.getMapper(CssRequestMapper.class);

    @Mappings({
            @Mapping(source = "eventDate", target = "eventDate", qualifiedByName = "localDateToString"),
            @Mapping(source = "requestDate", target = "requestDate", qualifiedByName = "localDateToString"),
            @Mapping(source = "requestStatus", target = "requestStatus")
    })
    CssRequestDto toDto(CssRequest entity);

    @Mappings({
            @Mapping(source = "eventDate", target = "eventDate", qualifiedByName = "stringToLocalDate"),
            @Mapping(target = "requestDate", expression = "java(java.time.LocalDateTime.now())")
    })
    CssRequest toEntity(CssRequestDto dto);


    @Mappings({
            @Mapping(target = "requestNo", source = "id.requestNo"),
            @Mapping(target = "attacheSerial", source = "id.attacheSerial"),
            @Mapping(source = "filePath", target = "fileBase64", qualifiedByName = "filePathToBase64")
    })
    CssRequestAttachmentDto toDto(CssRequestAttachment entity);

    @Mappings({
        @Mapping(target = "id.requestNo", source = "requestNo"),
        @Mapping(target = "id.attacheSerial", source = "attacheSerial")
    })
    CssRequestAttachment toEntity(CssRequestAttachmentDto dto);
}