package cms.core.service;

import cms.core.domain.BiddingResult;
import cms.core.domain.Proposal;
import cms.core.domain.Review;

import java.util.List;
import java.util.Optional;

public interface ProposalService {

    Optional<Proposal> getProposalById(Long proposalId);

    List<Proposal> getAll();

    List<Proposal> getAllByConferenceId(Long conferenceId);

    Proposal save(Proposal proposal);

    void delete(Long proposalId);

    Proposal update(Proposal newProposal);

    BiddingResult bidProposal(BiddingResult biddingResult);

    Review reviewProposal(Review review);
}
