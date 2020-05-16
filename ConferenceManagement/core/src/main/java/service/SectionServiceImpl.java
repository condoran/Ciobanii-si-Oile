package service;

import domain.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repo.SectionRepository;

import java.util.List;
import java.util.Optional;

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
    public Section save(Section section) {
        return sectionRepository.save(section);
    }

    @Override
    public void delete(Long sectionId) {
        sectionRepository.deleteById(sectionId);
    }
}
