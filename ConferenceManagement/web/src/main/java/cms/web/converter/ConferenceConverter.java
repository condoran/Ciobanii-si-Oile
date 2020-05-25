package cms.web.converter;

import cms.core.domain.Conference;
import cms.core.service.SectionService;
import cms.core.service.SectionServiceImpl;
import cms.web.dto.ConferenceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConferenceConverter extends BaseConverter<Conference, ConferenceDTO>{

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private SectionConverter sectionConverter;

    @Autowired
    private SectionService sectionService;

    @Override
    public Conference convertDtoToModel(ConferenceDTO conferenceDTO) {
        if(conferenceDTO == null)
            return null;
        return Conference.builder()
                .id(conferenceDTO.getId())
                .name(conferenceDTO.getName())
                .startDate(conferenceDTO.getEndDate())
                .endDate(conferenceDTO.getEndDate())
                .callForPapers(conferenceDTO.getCallForPapers())
                .abstractPaperDeadline(conferenceDTO.getAbstractPaperDeadline())
                .fullPaperDeadline(conferenceDTO.getFullPaperDeadline())
                .biddingDeadline(conferenceDTO.getBiddingDeadline())
                .password(conferenceDTO.getPassword())
                .chair((conferenceDTO.getChair() == null) ? null : userConverter.convertDtoToModel(conferenceDTO.getChair()))
                .firstCoChair((conferenceDTO.getFirstCoChair() == null) ? null : userConverter.convertDtoToModel(conferenceDTO.getFirstCoChair()))
                .secondCoChair((conferenceDTO.getSecondCoChair() == null) ? null : userConverter.convertDtoToModel(conferenceDTO.getSecondCoChair()))
                .sections((conferenceDTO.getSectionsIDs() == null) ? null : sectionService.getSectionsByIDs(conferenceDTO.getSectionsIDs()))
                .build();
    }

    @Override
    public ConferenceDTO convertModelToDto(Conference conference) {
        if(conference == null)
            return null;
        return ConferenceDTO.builder()
                .id(conference.getId())
                .name(conference.getName())
                .startDate(conference.getStartDate())
                .endDate(conference.getEndDate())
                .callForPapers(conference.getCallForPapers())
                .abstractPaperDeadline(conference.getAbstractPaperDeadline())
                .fullPaperDeadline(conference.getFullPaperDeadline())
                .biddingDeadline(conference.getBiddingDeadline())
                .password(conference.getPassword())
                .chair((conference.getChair() == null) ? null : userConverter.convertModelToDto(conference.getChair()))
                .firstCoChair((conference.getFirstCoChair() == null) ? null : userConverter.convertModelToDto(conference.getFirstCoChair()))
                .secondCoChair((conference.getSecondCoChair() == null) ? null : userConverter.convertModelToDto(conference.getSecondCoChair()))
                .sectionsIDs((conference.getSections() == null) ? null : sectionConverter.convertModelsToIDs(conference.getSections()))
                .build();
    }
}
