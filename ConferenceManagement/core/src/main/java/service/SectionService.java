package service;

import domain.Section;

import java.util.List;

public interface SectionService {

    List<Section> getAll();

    Section save(Section section);

    void delete(Long id);
}
