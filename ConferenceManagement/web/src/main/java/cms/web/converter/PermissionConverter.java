package cms.web.converter;

import cms.core.domain.Permission;
import cms.web.dto.PermissionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PermissionConverter extends BaseConverter<Permission, PermissionDTO>{

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private ConferenceConverter conferenceConverter;

    @Override
    public Permission convertDtoToModel(PermissionDTO permissionDTO) {
        return Permission.builder()
                .id(permissionDTO.getId())
                .cmsUser(userConverter.convertDtoToModel(permissionDTO.getUser()))
                .conference(conferenceConverter.convertDtoToModel(permissionDTO.getConference()))
                .isAuthor(permissionDTO.isAuthor())
                .isPCMember(permissionDTO.isPCMember())
                .isSectionChair(permissionDTO.isSectionChair())
                .build();
    }

    @Override
    public PermissionDTO convertModelToDto(Permission permission) {
        return PermissionDTO.builder()
                .id(permission.getId())
                .user(userConverter.convertModelToDto(permission.getCmsUser()))
                .conference(conferenceConverter.convertModelToDto(permission.getConference()))
                .isAuthor(permission.isAuthor())
                .isPCMember(permission.isPCMember())
                .isSectionChair(permission.isSectionChair())
                .build();
    }
}