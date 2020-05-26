package cms.web.converter;

import cms.core.domain.Section;
import cms.core.service.UserService;
import cms.web.dto.SectionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SectionConverter extends BaseConverter<Section, SectionDTO>{

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private ConferenceConverter conferenceConverter;

    @Autowired
    private UserService userService;

    @Override
    public Section convertDtoToModel(SectionDTO sectionDTO) {
        if(sectionDTO == null)
            return null;
        return Section.builder()
                .id(sectionDTO.getId())
                .sectionChair((sectionDTO.getChair() == null)? null : userConverter.convertDtoToModel(sectionDTO.getChair()))
                .speakers((sectionDTO.getSpeakersIDs() == null)? null : userService.getUsersByIDs(sectionDTO.getSpeakersIDs()))
                .conference((sectionDTO.getConference() == null)? null : conferenceConverter.convertDtoToModel(sectionDTO.getConference()))
                .build();
    }

    @Override
    public SectionDTO convertModelToDto(Section section) {
        if(section == null)
            return null;
        return SectionDTO.builder()
                .id(section.getId())
                .chair((section.getSectionChair() == null)? null : userConverter.convertModelToDto(section.getSectionChair()))
                .speakersIDs((section.getSpeakers() == null)? null : userConverter.convertModelsToIDs(section.getSpeakers()))
                .conference((section.getConference() == null)? null : conferenceConverter.convertModelToDto(section.getConference()))
                .build();
    }

    public List<Long> convertModelsToIDs(List<Section> models) {
        return models.stream()
                .map(Section::getId)
                .collect(Collectors.toList());
    }

}
