package com.smartjob.authservice.user.infraestructure.mappers;
import com.smartjob.authservice.phone.domains.data.PhoneDto;
import com.smartjob.authservice.phone.infraestructure.entities.PhoneEntity;
import com.smartjob.authservice.phone.infraestructure.mappers.PhoneMapper;
import com.smartjob.authservice.user.domains.data.UserDto;
import com.smartjob.authservice.user.infraestructure.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = PhoneMapper.class)
public interface UserMapper {
    @Mapping(target = "phones", qualifiedByName = "toPhoneDto")
    UserDto toUserDto(UserEntity userEntity);
    @Mapping(target = "phones", qualifiedByName = "toPhoneDto")
    List<UserDto> toUserDto(List<UserEntity>  userEntityList);
    @Mapping(target = "phones", qualifiedByName = "toPhoneEntity")
    UserEntity toUserEntity(UserDto userDto);

}
