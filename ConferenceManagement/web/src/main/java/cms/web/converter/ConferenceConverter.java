package cms.web.converter;

import cms.core.domain.Conference;
import cms.web.dto.ConferenceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConferenceConverter extends BaseConverter<Conference, ConferenceDTO>{

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private SectionConverter sectionConverter;

    @Override
    public Conference convertDtoToModel(ConferenceDTO conferenceDTO) {
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
                .chair(userConverter.convertDtoToModel(conferenceDTO.getChair()))
                .firstCoChair(userConverter.convertDtoToModel(conferenceDTO.getFirstCoChair()))
                .secondCoChair(userConverter.convertDtoToModel(conferenceDTO.getSecondCoChair()))
                .sections(sectionConverter.convertDtosToModel(conferenceDTO.getSections()))
                .build();
    }

    @Override
    public ConferenceDTO convertModelToDto(Conference conference) {
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
                .chair(userConverter.convertModelToDto(conference.getChair()))
                .firstCoChair(userConverter.convertModelToDto(conference.getFirstCoChair()))
                .secondCoChair(userConverter.convertModelToDto(conference.getSecondCoChair()))
                .sections(sectionConverter.convertModelsToDtos(conference.getSections()))
                .build();
    }
}
