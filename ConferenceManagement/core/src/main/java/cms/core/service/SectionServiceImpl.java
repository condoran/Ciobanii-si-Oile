package cms.core.service;

import cms.core.domain.CMSUser;
import cms.core.domain.Proposal;
import cms.core.domain.Section;
import cms.core.repo.ProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cms.core.repo.SectionRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SectionServiceImpl implements SectionService{

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private ProposalRepository proposalRepository;

    @Override
    public Optional<Section> getSectionById(Long sectionId) {
        return sectionRepository.findById(sectionId);
    }

    @Override
    public List<Section> getSectionsByIDs(List<Long> sectionsIDs) {
        return sectionRepository.findAllById(sectionsIDs);
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

    @Override
    @Transactional
    public Optional<Section> updateSectionChair(Long sectionID, CMSUser futureChair) {
        Optional<Section> section = sectionRepository.findById(sectionID);
        if(section.isEmpty()){
            return Optional.empty();
        }

        section.get().setSectionChair(futureChair);
        return section;
    }

    @Override
    public List<Proposal> getUnassignedAndAcceptedProposals() {
        List<Long> assignedProposalIDs = new ArrayList<>();
        sectionRepository.findAll().stream()
                .map(Section::getProposals)
                .forEach(proposals -> proposals.forEach(proposal -> assignedProposalIDs.add(proposal.getId())));

        return proposalRepository.findAll().stream()
                .filter(proposal -> proposal.getStatus().equals("Accepted"))
                .filter(proposal -> !assignedProposalIDs.contains(proposal.getId()))
                .collect(Collectors.toList());
    }

}
