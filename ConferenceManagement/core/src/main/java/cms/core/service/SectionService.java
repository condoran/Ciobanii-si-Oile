package cms.core.service;

import cms.core.domain.Section;

import java.util.List;
import java.util.Optional;

public interface SectionService {

    Optional<Section> getSectionById(Long sectionId);

    List<Section> getAll();

    List<Section> getAllByConferenceId(Long conferenceId);

    Section save(Section section);

    void delete(Long sectionId);
}
