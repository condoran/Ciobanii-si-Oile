package cms.core.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import cms.core.service.ConferenceService;
import cms.core.service.ProposalService;
import cms.core.service.SectionService;
import cms.core.service.UserService;

@Component
public class Console {
    @Autowired
    private UserService userService;

    @Autowired
    private ProposalService proposalService;

    @Autowired
    private ConferenceService conferenceService;

    @Autowired
    private SectionService sectionService;

    public void run(){
//        CMSUser cmsUser = CMSUser.builder()
//                .name("tim")
//                .affiliation("adsfsdf")
//                .emailAddress("tim@yahoo.com")
//                .build();
//        userService.save(cmsUser);
//        Section section = Section.builder()
//                .speakers(Arrays.asList(cmsUser))
//                .build();
//        sectionService.save(section);
//        userService.save(cmsUser);
//        Proposal proposal = Proposal.builder()
//                .keywords("politicieni")
//                .authors(Arrays.asList(cmsUser))
//                .build();
//        proposalService.save(proposal);
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
