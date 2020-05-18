package service;

import domain.BiddingResult;
import domain.Proposal;
import domain.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repo.BiddingRepository;
import repo.ProposalRepository;
import repo.ReviewRepository;


import java.util.List;
import java.util.Optional;

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
