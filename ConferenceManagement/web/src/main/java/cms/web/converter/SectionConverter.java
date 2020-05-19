package cms.web.converter;

import cms.core.domain.Section;
import cms.web.dto.SectionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SectionConverter extends BaseConverter<Section, SectionDTO>{

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private ConferenceConverter conferenceConverter;

    @Override
    public Section convertDtoToModel(SectionDTO sectionDTO) {
        return Section.builder()
                .id(sectionDTO.getId())
                .sectionChair(userConverter.convertDtoToModel(sectionDTO.getChair()))
                .speakers(userConverter.convertDtosToModel(sectionDTO.getSpeakers()))
                .conference(conferenceConverter.convertDtoToModel(sectionDTO.getConference()))
                .build();
    }

    @Override
    public SectionDTO convertModelToDto(Section section) {
        return SectionDTO.builder()
                .id(section.getId())
                .chair(userConverter.convertModelToDto(section.getSectionChair()))
                .speakers(userConverter.convertModelsToDtos(section.getSpeakers()))
                .conference(conferenceConverter.convertModelToDto(section.getConference()))
                .build();
    }
}
