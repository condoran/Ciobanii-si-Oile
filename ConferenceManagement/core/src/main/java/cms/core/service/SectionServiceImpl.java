package cms.core.service;

import cms.core.domain.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cms.core.repo.SectionRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SectionServiceImpl implements SectionService{

    @Autowired
    private SectionRepository sectionRepository;

    @Override
    public Optional<Section> getSectionById(Long sectionId) {
        return sectionRepository.findById(sectionId);
    }

    @Override
    public List<Section> getAll() {
        return sectionRepository.findAll();
    }

    @Override
    public List<Section> getAllByConferenceId(Long conferenceId) {
        return sectionRepository.findAll().stream().filter(section -> section.getConference().getId() == conferenceId)
                .collect(Collectors.toList());
    }

    @Override
    public Section save(Section section) {
        return sectionRepository.save(section);
    }

    @Override
    public void delete(Long sectionId) {
        sectionRepository.deleteById(sectionId);
    }
}
