package cms.web.converter;

import cms.core.domain.CMSUser;
import cms.web.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserConverter extends BaseConverter<CMSUser, UserDTO> {
    @Override
    public CMSUser convertDtoToModel(UserDTO userDTO) {
        return CMSUser.builder()
                .id(userDTO.getId())
                .affiliation(userDTO.getAffiliation())
                .emailAddress(userDTO.getEmailAddress())
                .isChair(userDTO.isChair())
                .isCoChair(userDTO.isCoChair())
                .isSCMember(userDTO.isSCMember())
                .name(userDTO.getName())
                .password(userDTO.getPassword())
                .personalWebsite(userDTO.getPersonalWebsite())
                .username(userDTO.getUsername())
                .build();
    }

    @Override
    public UserDTO convertModelToDto(CMSUser user) {
        return UserDTO.builder()
                .id(user.getId())
                .affiliation(user.getAffiliation())
                .emailAddress(user.getEmailAddress())
                .isChair(user.isChair())
                .isCoChair(user.isCoChair())
                .isSCMember(user.isSCMember())
                .name(user.getName())
                .password(user.getPassword())
                .personalWebsite(user.getPersonalWebsite())
                .username(user.getUsername())
                .build();
    }
}