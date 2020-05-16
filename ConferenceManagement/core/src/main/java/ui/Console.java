package ui;

import domain.CMSUser;
import domain.Conference;
import domain.Proposal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.ConferenceService;
import service.ProposalService;
import service.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class Console {
    @Autowired
    private UserService userService;

    @Autowired
    private ProposalService proposalService;

    @Autowired
    private ConferenceService conferenceService;

    public void run(){
//        CMSUser cmsUser = CMSUser.builder()
//                .name("tim")
//                .affiliation("adsfsdf")
//                .emailAddress("tim@yahoo.com")
//                .isPCMember(true)
//                .build();
//        userService.save(cmsUser);
//        Conference conference = Conference.builder()
//                .name("conf")
//                .PCMembers(Arrays.asList(cmsUser))
//                .build();
//        conferenceService.save(conference);
//        Proposal proposal1 = Proposal.builder()
//                .keywords("keywords")
//                .name("proposal")
//                .authors(Arrays.asList(cmsUser))
//                .build();
//        proposalService.save(proposal1);
//
//        Proposal proposal2 = Proposal.builder()
//                .keywords("keywords1")
//                .name("proposal")
//                .authors(Arrays.asList(cmsUser))
//                .build();
//        proposalService.save(proposal2);
    }
}
