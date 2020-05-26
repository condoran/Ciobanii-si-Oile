package cms.core.service;

import cms.core.domain.*;
import cms.core.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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

    @Autowired
    private ProposalAuthorRepository proposalAuthorRepository;

    @Autowired
    private UserRepository userRepository;

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
        return proposalRepository.findAll().stream().filter(proposal ->
            proposal.getConference().getId().equals(conferenceId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Long> getProposalsIDsForUser(Long userID) {
        return proposalAuthorRepository.findAll().stream().filter(x -> x.getUser().getId().equals(userID))
                .map(x -> x.getProposal().getId())
                .collect(Collectors.toList());
    }

    @Override
    public List<Long> getUnbiddenIDs(Long userID) {
        List<Long> allProposalsIDs = proposalRepository.findAll().stream()
                .map(Proposal::getId)
                .collect(Collectors.toList());
        List<Long> biddenIDs = biddingRepository.findAll().stream()
                .map(bidding -> bidding.getProposal().getId())
                .collect(Collectors.toList());
        return allProposalsIDs.stream().filter(id -> !biddenIDs.contains(id))
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
        proposal.setAbstractPaper(newProposal.getAbstractPaper());
        proposal.setFullPaper(newProposal.getFullPaper());
        proposal.setConference(newProposal.getConference());

        return proposal;
    }

    @Override
    public Bidding bidProposal(Bidding bidding) {
        return biddingRepository.save(bidding);
    }

    @Override
    public Review reviewProposal(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public ProposalAuthor addAuthorForProposal(ProposalAuthor proposalAuthor) {
        return proposalAuthorRepository.save(proposalAuthor);
    }

    @Override
    public List<CMSUser> getUsersForReviewingAProposal(Long proposalID) {
        List<Long> userIDs = biddingRepository.findAll().stream()
                .filter(bidding -> bidding.getProposal().getId().equals(proposalID))
                .filter(Bidding::getAccepted)
                .map(bidding -> bidding.getCMSUser().getId())
                .collect(Collectors.toList());

        return userRepository.findAllById(userIDs);
    }

    @Override
    public List<Long> getReviewersForProposal(Long proposalID) {
        return reviewRepository.findAll().stream()
                .filter(review -> review.getProposal().getId().equals(proposalID))
                .map(review -> review.getCMSUser().getId())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Optional<Review> updateReview(Review review) {
        Review newReview = reviewRepository.findById(review.getId()).orElse(null);

        if(newReview == null)
            return Optional.empty();

        newReview.setQualifier(review.getQualifier());
        newReview.setRecommendation(review.getRecommendation());

        return Optional.of(newReview);
    }

    @Override
    public Optional<Review> getReviewByUserAndProposal(Long userID, Long proposalID) {
        return reviewRepository.findAll().stream().filter(review -> review.getCMSUser().getId().equals(userID)
            && review.getProposal().getId().equals(proposalID)).findFirst();
    }


}
