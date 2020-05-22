package cms.web.converter;

import cms.core.domain.Proposal;
import cms.core.service.UserService;
import cms.web.dto.ProposalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProposalConverter extends BaseConverter<Proposal, ProposalDTO>{
    @Autowired
    private UserConverter userConverter;

    @Autowired
    private ConferenceConverter conferenceConverter;

    @Autowired
    private UserService userService;

    @Override
    public Proposal convertDtoToModel(ProposalDTO proposalDTO) {
        if(proposalDTO == null)
            return null;
        return Proposal.builder()
                .id(proposalDTO.getId())
                .name(proposalDTO.getName())
                .topics(proposalDTO.getTopics())
                .keywords(proposalDTO.getKeywords())
                .abstractPaper(null)
                .fullPaper(null)
                .conference(conferenceConverter.convertDtoToModel(proposalDTO.getConference()))
                .build();
    }

    @Override
    public ProposalDTO convertModelToDto(Proposal proposal) {
        if(proposal == null)
            return null;
        return ProposalDTO.builder()
                .id(proposal.getId())
                .name(proposal.getName())
                .topics(proposal.getTopics())
                .keywords(proposal.getKeywords())
                .conference(conferenceConverter.convertModelToDto(proposal.getConference()))
                .build();
    }
}
