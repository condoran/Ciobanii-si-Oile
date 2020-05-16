package service;

import domain.BiddingResult;
import domain.Proposal;
import domain.Review;

import java.util.List;

public interface ProposalService {

    List<Proposal> getAll();

    Proposal save(Proposal proposal);

    void delete(Long id);

    Proposal update(Proposal newProposal);

    BiddingResult bidProposal(BiddingResult biddingResult);

    Review reviewProposal(Review review);
}
