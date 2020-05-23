package cms.web.converter;

import cms.core.domain.Bidding;
import cms.web.dto.BiddingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BiddingConverter extends BaseConverter<Bidding, BiddingDTO>{
    @Autowired
    private ProposalConverter proposalConverter;

    @Autowired
    private UserConverter userConverter;
    @Override
    public Bidding convertDtoToModel(BiddingDTO biddingDTO) {
        if(biddingDTO == null)
            return null;
        return Bidding.builder()
                .id(biddingDTO.getId())
                .proposal(proposalConverter.convertDtoToModel(biddingDTO.getProposal()))
                .CMSUser(userConverter.convertDtoToModel(biddingDTO.getUser()))
                .accepted(biddingDTO.getAccepted())
                .build();
    }

    @Override
    public BiddingDTO convertModelToDto(Bidding bidding) {
        if(bidding == null)
            return null;
        return BiddingDTO.builder()
                .id(bidding.getId())
                .proposal(proposalConverter.convertModelToDto(bidding.getProposal()))
                .user(userConverter.convertModelToDto(bidding.getCMSUser()))
                .accepted(bidding.getAccepted())
                .build();
    }
}
