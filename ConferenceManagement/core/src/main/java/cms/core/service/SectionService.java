package cms.core.service;

import cms.core.domain.CMSUser;
import cms.core.domain.Section;

import java.util.List;
import java.util.Optional;

public interface SectionService {

    Optional<Section> getSectionById(Long sectionId);

    List<Section> getSectionsByIDs(List<Long> sectionsIDs);

    List<Section> getAll();

    List<Section> getAllByConferenceId(Long conferenceId);

    Section save(Section section);

    void delete(Long sectionId);

    Optional<Section> updateSectionChair(Long sectionID, CMSUser futureChair);

}
