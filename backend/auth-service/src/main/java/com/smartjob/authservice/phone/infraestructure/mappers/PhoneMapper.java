package com.smartjob.authservice.phone.infraestructure.mappers;

import com.smartjob.authservice.phone.domains.data.PhoneDto;
import com.smartjob.authservice.phone.infraestructure.entities.PhoneEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;


@Mapper(componentModel = "spring")
public interface PhoneMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "number", source = "number")
    @Mapping(target = "cityCode", source = "cityCode")
    @Mapping(target = "countryCode", source = "countryCode")
    @Mapping(target = "user", ignore = true)
    PhoneDto toPhoneDto(PhoneEntity phoneEntity);

    @Named("toPhoneDto")
    @Mapping(target = "id", source = "id")
    @Mapping(target = "number", source = "number")
    @Mapping(target = "cityCode", source = "cityCode")
    @Mapping(target = "countryCode", source = "countryCode")
    @Mapping(target = "user", ignore = true)
    List<PhoneDto> toPhoneDto(List<PhoneEntity>  phoneEntityList);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "number", source = "number")
    @Mapping(target = "cityCode", source = "cityCode")
    @Mapping(target = "countryCode", source = "countryCode")
    @Mapping(target = "user", source = "user")
    PhoneEntity toPhoneEntity(PhoneDto phoneDto);

    @Named("toPhoneEntity")
    @Mapping(target = "id", source = "id")
    @Mapping(target = "number", source = "number")
    @Mapping(target = "cityCode", source = "cityCode")
    @Mapping(target = "countryCode", source = "countryCode")
    @Mapping(target = "user", source = "user")
    List<PhoneEntity> toPhoneEntity(List<PhoneDto>  phoneDtoList);
}
