package cms.core.service;


import cms.core.domain.CMSUser;
import cms.core.domain.Conference;
import cms.core.domain.Permission;
import cms.core.domain.ProposalAuthor;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<CMSUser> getUserByUsername(String username);

    Optional<CMSUser> getUserByEmailAddress(String emailAddress);

    Optional<CMSUser> getUserById(Long userId);

    List<CMSUser> getUsersByIDs(List<Long> usersIDs);

    List<Conference> getConferencesForPCMember(String username);

    List<CMSUser> getAllUsers();

    CMSUser save(CMSUser CMSUser);

    CMSUser update(CMSUser newCMSUser);

    void delete(long userId);

    Optional<CMSUser> getChair();

    List<CMSUser> getCoChairs();

    List<CMSUser> getSCMembers();

    public List<CMSUser> getNonSCMembers();

    Optional<Permission> getPermissionForUserInConference(long userID, long conferenceID);

    Optional<ProposalAuthor> getUserCanBeAuthorInProposal(long userID, long proposalID);

}
