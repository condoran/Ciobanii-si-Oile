package cms.web.controller;

import cms.core.domain.CMSUser;
import cms.core.domain.Proposal;
import cms.core.domain.ProposalAuthor;
import cms.core.repo.ProposalRepository;
import cms.core.service.ProposalService;
import cms.web.converter.ProposalAuthorConverter;
import cms.web.converter.ProposalConverter;
import cms.web.dto.ProposalAuthorDTO;
import cms.web.dto.ProposalDTO;
import cms.web.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProposalController {
    @Autowired
    private ProposalService proposalService;

    @Autowired
    private ProposalConverter proposalConverter;

    @Autowired
    private ProposalAuthorConverter proposalAuthorConverter;

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
}
