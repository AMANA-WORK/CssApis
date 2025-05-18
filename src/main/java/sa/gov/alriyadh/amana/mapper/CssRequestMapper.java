package sa.gov.alriyadh.amana.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import sa.gov.alriyadh.amana.entity.CssRequestPhase;
import sa.gov.alriyadh.amana.entity.dto.CssRequestDto;
import sa.gov.alriyadh.amana.entity.CssRequest;
import sa.gov.alriyadh.amana.entity.CssRequestAttachment;
import sa.gov.alriyadh.amana.entity.dto.CssRequestAttachmentDto;
import sa.gov.alriyadh.amana.entity.dto.CssRequestPhaseDto;

@Mapper(componentModel = "spring", uses = {DateMapper.class, FileBase64Mapper.class})
public interface CssRequestMapper {

    CssRequestMapper INSTANCE = Mappers.getMapper(CssRequestMapper.class);

    @Mappings({
            @Mapping(source = "eventDate", target = "eventDate", qualifiedByName = "localDateToString"),
            @Mapping(source = "requestDate", target = "requestDate", qualifiedByName = "localDateToString"),
            @Mapping(source = "requestStatus", target = "requestStatus"),
            @Mapping(source = "requestNotes", target = "requestNotes")
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

    @Mappings({
            @Mapping(source = "createDate", target = "createDate", qualifiedByName = "localDateToString"),
    })
    CssRequestPhaseDto toDto(CssRequestPhase entity);

    @Mappings({
            @Mapping(source = "createDate", target = "createDate", qualifiedByName = "stringToLocalDate"),
    })
    CssRequestPhase toEntity(CssRequestPhaseDto dto);
}