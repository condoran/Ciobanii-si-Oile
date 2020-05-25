package cms.core.service;

import cms.core.domain.*;

import java.util.List;
import java.util.Optional;

public interface ProposalService {

    Optional<Proposal> getProposalById(Long proposalId);

    List<Proposal> getAll();

    List<Proposal> getAllByConferenceId(Long conferenceId);

    List<Long> getProposalsIDsForUser(Long userID);

    Proposal save(Proposal proposal);

    void delete(Long proposalId);

    Proposal update(Proposal newProposal);

    Bidding bidProposal(Bidding bidding);

    Review reviewProposal(Review review);

    ProposalAuthor addAuthorForProposal(ProposalAuthor proposalAuthor);
}
