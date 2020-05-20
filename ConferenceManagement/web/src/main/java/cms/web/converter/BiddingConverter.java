package cms.web.converter;

import cms.core.domain.BiddingResult;
import cms.web.dto.BiddingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BiddingConverter extends BaseConverter<BiddingResult, BiddingDTO>{
    @Autowired
    private ProposalConverter proposalConverter;

    @Autowired
    private UserConverter userConverter;
    @Override
    public BiddingResult convertDtoToModel(BiddingDTO biddingDTO) {
        if(biddingDTO == null)
            return null;
        return BiddingResult.builder()
                .id(biddingDTO.getId())
                .proposal(proposalConverter.convertDtoToModel(biddingDTO.getProposal()))
                .CMSUser(userConverter.convertDtoToModel(biddingDTO.getUser()))
                .accepted(biddingDTO.isAccepted())
                .build();
    }

    @Override
    public BiddingDTO convertModelToDto(BiddingResult biddingResult) {
        if(biddingResult == null)
            return null;
        return BiddingDTO.builder()
                .id(biddingResult.getId())
                .proposal(proposalConverter.convertModelToDto(biddingResult.getProposal()))
                .user(userConverter.convertModelToDto(biddingResult.getCMSUser()))
                .accepted(biddingResult.isAccepted())
                .build();
    }
}
