package cms.core.service;


import cms.core.domain.CMSUser;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<CMSUser> getUserByUsername(String username);

    Optional<CMSUser> getUserByEmailAddress(String emailAddress);

    List<CMSUser> getAllUsers();

    CMSUser save(CMSUser CMSUser);

    CMSUser update(CMSUser newCMSUser);

    void delete(long userId);

    Optional<CMSUser> getChair();

    List<CMSUser> getCoChairs();

    List<CMSUser> getPCMembers();

    List<CMSUser> getAuthors();

    List<CMSUser> getSCMembers();

}
