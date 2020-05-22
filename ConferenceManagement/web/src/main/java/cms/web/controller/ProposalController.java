package cms.web.controller;

import cms.core.domain.CMSUser;
import cms.core.domain.Proposal;
import cms.core.repo.ProposalRepository;
import cms.core.service.ProposalService;
import cms.web.converter.ProposalConverter;
import cms.web.dto.ProposalDTO;
import cms.web.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProposalController {
    @Autowired
    private ProposalService proposalService;

    @Autowired
    private ProposalConverter proposalConverter;

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

    @RequestMapping(value = "/proposal/updateProposal", method = RequestMethod.PUT)
    ProposalDTO updateProposal(@RequestBody ProposalDTO newProposalDTO){
        return proposalConverter.convertModelToDto(proposalService.update(proposalConverter.convertDtoToModel(newProposalDTO)));
    }
}
