package service;


import domain.CMSUser;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<CMSUser> getAllUsers();

    CMSUser save(CMSUser CMSUser);

    CMSUser update(CMSUser newCMSUser);

    void delete(long id);

    Optional<CMSUser> getChair();

    List<CMSUser> getCoChairs();

    List<CMSUser> getPCMembers();

    List<CMSUser> getAuthors();

    List<CMSUser> getSCMembers();

}
