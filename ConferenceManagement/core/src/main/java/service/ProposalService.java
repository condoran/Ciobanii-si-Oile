package service;

import domain.BiddingResult;
import domain.Proposal;
import domain.Review;

import java.util.List;
import java.util.Optional;

public interface ProposalService {

    Optional<Proposal> getProposalById(Long proposalId);

    List<Proposal> getAll();

    Proposal save(Proposal proposal);

    void delete(Long proposalId);

    Proposal update(Proposal newProposal);

    BiddingResult bidProposal(BiddingResult biddingResult);

    Review reviewProposal(Review review);
}
