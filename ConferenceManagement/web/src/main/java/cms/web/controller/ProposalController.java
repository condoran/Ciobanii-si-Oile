package cms.web.controller;

import cms.core.domain.*;
import cms.core.repo.ProposalRepository;
import cms.core.service.ProposalService;
import cms.web.converter.*;
import cms.web.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ProposalController {
    public static final Logger logger = LoggerFactory.getLogger(ConferenceController.class);

    @Autowired
    private ProposalService proposalService;

    @Autowired
    private ProposalConverter proposalConverter;

    @Autowired
    private ProposalAuthorConverter proposalAuthorConverter;

    @Autowired
    private BiddingConverter biddingConverter;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private ReviewConverter reviewConverter;

    @RequestMapping(value = "/proposal/getProposals", method = RequestMethod.GET)
    List<ProposalDTO> getProposals(){
        List<Proposal> proposals = proposalService.getAll();
        return new ArrayList<>(proposalConverter.convertModelsToDtos(proposals));
    }

    @RequestMapping(value = "/proposal/getProposalsForConference", method = RequestMethod.POST)
    List<ProposalDTO> getProposalsForConference(@RequestBody Long conferenceID){
        List<Proposal> proposals = proposalService.getAllByConferenceId(conferenceID);
        return new ArrayList<>(proposalConverter.convertModelsToDtos(proposals));
    }

    @RequestMapping(value = "/proposal/saveProposal", method = RequestMethod.POST)
    ProposalDTO saveProposal(@RequestBody ProposalDTO proposalDTO){
        return proposalConverter.convertModelToDto(proposalService.save(proposalConverter.convertDtoToModel(proposalDTO)));
    }

    @RequestMapping(value = "/proposal/addAuthorForProposal", method = RequestMethod.POST)
    ProposalAuthorDTO addAuthorForProposal(@RequestBody ProposalAuthorDTO proposalAuthorDTO){
        ProposalAuthor proposalAuthor = proposalAuthorConverter
                .convertDtoToModel(proposalAuthorDTO);
        return proposalAuthorConverter.convertModelToDto(
                proposalService.addAuthorForProposal(proposalAuthor));
    }

    @RequestMapping(value = "/proposal/updateProposal", method = RequestMethod.POST)
    ProposalDTO updateProposal(@RequestBody ProposalDTO newProposalDTO){
        return proposalConverter.convertModelToDto(proposalService.update(proposalConverter.convertDtoToModel(newProposalDTO)));
    }

    @RequestMapping(value = "/proposal/getProposalsIDsForUser", method = RequestMethod.POST)
    List<Long> getProposalsIDsForUser(@RequestBody Long userID){
        return proposalService.getProposalsIDsForUser(userID);
    }

    @RequestMapping(value ="/proposal/bidProposal", method = RequestMethod.POST)
    BiddingDTO bidProposal(@RequestBody BiddingDTO biddingDTO){
        Bidding bidding = proposalService.bidProposal(biddingConverter.convertDtoToModel(biddingDTO));
        return biddingConverter.convertModelToDto(bidding);
    }

    @RequestMapping(value = "/proposal/reviewProposal", method = RequestMethod.POST)
    ReviewDTO reviewProposal(@RequestBody ReviewDTO reviewDTO){
        logger.trace("proposalController, review proposal, review = {}", reviewDTO);
        Review review = proposalService.reviewProposal(reviewConverter.convertDtoToModel(reviewDTO));
        logger.trace("proposalController, review proposal, saved review = {}", review);
        return reviewConverter.convertModelToDto(review);
    }

    @RequestMapping(value = "/proposal/getUnbiddenProposalsIDs", method = RequestMethod.POST)
    List<Long> getUnbiddenProposalsIDs(@RequestBody Long userID) {
        return proposalService.getUnbiddenIDs(userID);
    }

    @RequestMapping(value = "/proposal/getUsersForReviewingAProposal", method = RequestMethod.POST)
    List<UserDTO> getUsersForReviewingAProposal(@RequestBody Long proposalID){
        List<CMSUser> users = proposalService.getUsersForReviewingAProposal(proposalID);
        return new ArrayList<>(userConverter.convertModelsToDtos(users));
    }

    @RequestMapping(value = "/proposal/getReviewersForProposal", method = RequestMethod.POST)
    List<Long> getReviewersForProposal(@RequestBody Long proposalID){
        return proposalService.getReviewersForProposal(proposalID);
    }

    @RequestMapping(value = "/proposal/updateReview", method = RequestMethod.POST)
    ReviewDTO updateReview(@RequestBody ReviewDTO reviewDTO){
        Optional<Review> updatedReview = proposalService.updateReview(reviewConverter.convertDtoToModel(reviewDTO));

        if(updatedReview.isEmpty()){
            return null;
        }

        return reviewConverter.convertModelToDto(updatedReview.get());
    }

    @RequestMapping(value = "/proposal/getReviewByUserAndProposal", method = RequestMethod.POST)
    ReviewDTO getReviewByUserAndProposal(@RequestBody Long[] IDs){
        Optional<Review> review = proposalService.getReviewByUserAndProposal(IDs[0], IDs[1]);

        if(review.isEmpty()){
            return null;
        }
        return reviewConverter.convertModelToDto(review.get());
    }

    @RequestMapping(value = "/proposal/getReviewsForProposal", method = RequestMethod.POST)
    List<ReviewDTO> getReviewsForProposal(@RequestBody Long proposalID) {
        logger.trace("in proposalController, getReviewsForProposal, proposalID = {}", proposalID);
        List<Review> reviewList = proposalService.getReviewsForProposal(proposalID);
        logger.trace("in proposalController, getReviewsForProposal, reviewList = {}", reviewList);
        List<ReviewDTO> reviewDTOList = reviewConverter.convertModelsToDtos(reviewList);
        logger.trace("in proposalController, getReviewsForProposal, reviewDTOList = {}", reviewDTOList);
        return reviewDTOList;
    }

    @RequestMapping(value = "/proposal/checkAuthorWroteAProposal", method = RequestMethod.POST)
    boolean checkAuthorWroteAProposal(@RequestBody Long[] IDs){
        return proposalService.checkAuthorWroteAProposal(IDs[0], IDs[1]);
    }

    @RequestMapping(value = "/proposal/checkProposalStatus", method = RequestMethod.POST)
    String checkProposalStatus(@RequestBody Long[] params){
        return proposalService.checkProposalStatus(params[0], params[1]);
    }
}
