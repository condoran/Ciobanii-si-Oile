package cms.core.service;

import cms.core.domain.CMSUser;
import cms.core.domain.Conference;
import cms.core.domain.Permission;
import cms.core.domain.ProposalAuthor;
import cms.core.repo.PermissionRepository;
import cms.core.repo.ProposalAuthorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cms.core.repo.UserRepository;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    public static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private ProposalAuthorRepository proposalAuthorRepository;

    @Override
    public Optional<CMSUser> getUserByUsername(String username) {
        return userRepository.findAll().stream().filter(user -> user.getUsername().equals(username)).findFirst();
    }

    @Override
    public Optional<CMSUser> getUserByEmailAddress(String emailAddress) {
        return userRepository.findAll().stream().filter(user -> user.getEmailAddress().equals(emailAddress)).findFirst();
    }

    @Override
    public Optional<CMSUser> getUserById(Long userId) {
        return userRepository.findAll().stream().filter(user -> user.getId().equals(userId)).findFirst();
    }

    @Override
    public List<CMSUser> getUsersByIDs(List<Long> usersIDs) {
        return userRepository.findAllById(usersIDs);
    }

    @Override
    public List<Conference> getConferencesForPCMember(String username) {
        System.out.println("innnn getConferencesForPCMember" + username);
        return permissionRepository.findAll()
                .stream()
                .filter(permission -> permission.getCmsUser().getUsername().equals(username))
                .filter(Permission::getIsPCMember)
                .map(Permission::getConference)
                .collect(Collectors.toList());
    }

    @Override
    public List<CMSUser> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public CMSUser save(CMSUser CMSUser) {
        logger.trace("save Service - method entered CMSUser={}", CMSUser);
        return userRepository.save(CMSUser);
    }

    @Override
    @Transactional
    public CMSUser update(CMSUser newCMSUser) {
        CMSUser cmsUser = userRepository.findById(newCMSUser.getId()).orElse(newCMSUser);

        cmsUser.setAffiliation(cmsUser.getAffiliation());
        cmsUser.setEmailAddress(newCMSUser.getEmailAddress());
        cmsUser.setIsChair(newCMSUser.getIsChair());
        cmsUser.setIsCoChair(newCMSUser.getIsCoChair());
        cmsUser.setIsSCMember(newCMSUser.getIsSCMember());
        cmsUser.setName(newCMSUser.getName());
        cmsUser.setUsername(newCMSUser.getUsername());
        cmsUser.setPassword(newCMSUser.getPassword());
        cmsUser.setPersonalWebsite(newCMSUser.getPersonalWebsite());

        return cmsUser;
    }

    @Override
    public void delete(long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public Optional<CMSUser> getChair() {
        return userRepository.findAll().stream().filter(CMSUser::getIsChair).findFirst();
    }

    @Override
    public List<CMSUser> getCoChairs() {
        return userRepository.findAll().stream().filter(user -> user.getIsCoChair() != null && user.getIsCoChair() == true).collect(Collectors.toList());
    }

    @Override
    public List<CMSUser> getSCMembers() {
        return userRepository.findAll().stream().filter(CMSUser::getIsSCMember).collect(Collectors.toList());
    }

    @Override
    public List<CMSUser> getNonSCMembers() {
        return userRepository.findAll().stream().filter(user -> !user.getIsSCMember()).collect(Collectors.toList());
    }

    @Override
    public Optional<Permission> getPermissionForUserInConference(long userID, long conferenceID) {
        return permissionRepository.findAll().stream().filter(permission -> permission.getCmsUser().getId() == userID
        && permission.getConference().getId() == conferenceID).findFirst();
    }

    @Override
    public Optional<ProposalAuthor> getUserCanBeAuthorInProposal(long userID, long proposalID) {
        return proposalAuthorRepository.findAll().stream().filter(proposalAuthor -> proposalAuthor.getUser().getId() == userID
        && proposalAuthor.getProposal().getId() == proposalID).findFirst();
    }


}
