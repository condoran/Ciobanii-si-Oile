package service;

import domain.CMSUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repo.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<CMSUser> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public CMSUser save(CMSUser CMSUser) {
        return userRepository.save(CMSUser);
    }

    @Override
    @Transactional
    public CMSUser update(CMSUser newCMSUser) {
        CMSUser cmsUser = userRepository.findById(newCMSUser.getId()).orElse(newCMSUser);

        cmsUser.setAffiliation(cmsUser.getAffiliation());
        cmsUser.setEmailAddress(newCMSUser.getEmailAddress());
        cmsUser.setChair(newCMSUser.isChair());
        cmsUser.setCoChair(newCMSUser.isCoChair());
        cmsUser.setPCMember(newCMSUser.isPCMember());
        cmsUser.setSCMember(newCMSUser.isSCMember());
        cmsUser.setAuthor(newCMSUser.isAuthor());
        cmsUser.setName(newCMSUser.getName());
        cmsUser.setUsername(newCMSUser.getUsername());
        cmsUser.setPassword(newCMSUser.getPassword());
        cmsUser.setPersonalWebsite(newCMSUser.getPersonalWebsite());
        cmsUser.setConferences(cmsUser.getConferences());

        return null;
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<CMSUser> getChair() {
        return userRepository.findAll().stream().filter(CMSUser::isChair).findFirst();
    }

    @Override
    public List<CMSUser> getCoChairs() {
        return userRepository.findAll().stream().filter(CMSUser::isCoChair).collect(Collectors.toList());
    }

    @Override
    public List<CMSUser> getPCMembers() {
        return userRepository.findAll().stream().filter(CMSUser::isPCMember).collect(Collectors.toList());
    }

    @Override
    public List<CMSUser> getAuthors() {
        return userRepository.findAll().stream().filter(CMSUser::isAuthor).collect(Collectors.toList());
    }

    @Override
    public List<CMSUser> getSCMembers() {
        return userRepository.findAll().stream().filter(CMSUser::isSCMember).collect(Collectors.toList());
    }
}
