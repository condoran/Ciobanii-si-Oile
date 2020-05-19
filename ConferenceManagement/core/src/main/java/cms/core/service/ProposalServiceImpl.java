package cms.core.service;

import cms.core.domain.BiddingResult;
import cms.core.domain.Proposal;
import cms.core.domain.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cms.core.repo.BiddingRepository;
import cms.core.repo.ProposalRepository;
import cms.core.repo.ReviewRepository;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProposalServiceImpl implements ProposalService{
    @Autowired
    private ProposalRepository proposalRepository;

    @Autowired
    private BiddingRepository biddingRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public Optional<Proposal> getProposalById(Long proposalId) {
        return proposalRepository.findById(proposalId);
    }

    @Override
    public List<Proposal> getAll() {
        return proposalRepository.findAll();
    }

    @Override
    public List<Proposal> getAllByConferenceId(Long conferenceId) {
        return proposalRepository.findAll().stream().filter(proposal -> proposal.getConference().getId().equals(conferenceId))
                .collect(Collectors.toList());
    }

    @Override
    public Proposal save(Proposal proposal) {
        return proposalRepository.save(proposal);
    }

    @Override
    public void delete(Long proposalId) {
        proposalRepository.deleteById(proposalId);
    }

    @Override
    @Transactional
    public Proposal update(Proposal newProposal) {
        Proposal proposal = proposalRepository.findById(newProposal.getId()).orElse(newProposal);

        proposal.setKeywords(newProposal.getKeywords());
        proposal.setName(newProposal.getName());
        proposal.setTopics(newProposal.getTopics());
        proposal.setAuthors(newProposal.getAuthors());
        proposal.setAbstractPaper(newProposal.getAbstractPaper());
        proposal.setFullPaper(newProposal.getFullPaper());
        proposal.setConference(newProposal.getConference());

        return proposal;
    }

    @Override
    public BiddingResult bidProposal(BiddingResult biddingResult) {
        return biddingRepository.save(biddingResult);
    }

    @Override
    public Review reviewProposal(Review review) {
        return reviewRepository.save(review);
    }
}
