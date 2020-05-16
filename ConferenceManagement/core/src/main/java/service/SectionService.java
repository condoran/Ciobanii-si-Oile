package service;

import domain.CMSUser;
import domain.Section;

import java.util.List;
import java.util.Optional;

public interface SectionService {

    Optional<Section> getSectionById(Long sectionId);

    List<Section> getAll();

    Section save(Section section);

    void delete(Long sectionId);
}
