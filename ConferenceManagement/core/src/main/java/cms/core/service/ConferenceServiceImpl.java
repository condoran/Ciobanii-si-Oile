package cms.core.service;

import cms.core.domain.CMSUser;
import cms.core.domain.Conference;
import cms.core.domain.Permission;
import cms.core.domain.Section;
import cms.core.repo.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cms.core.repo.ConferenceRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConferenceServiceImpl implements ConferenceService {
    @Autowired
    private ConferenceRepository conferenceRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public Optional<Conference> getConferenceById(Long conferenceId) {
        return conferenceRepository.findById(conferenceId);
    }

    @Override
    public List<Conference> getAll() {
        return conferenceRepository.findAll();
    }

    @Override
    public List<CMSUser> getPCMembersForConference(Long conferenceId) {
        return permissionRepository.findAll().stream()
                .filter(permission -> permission.getConference().getId().equals(conferenceId))
                .filter(Permission::isPCMember)
                .map(Permission::getCmsUser)
                .collect(Collectors.toList());
    }

    @Override
    public List<Section> getSectionsForConference(Long conferenceId) {
        return conferenceRepository.findAll().stream().filter(conference -> conference.getId().equals(conferenceId))
                .map(Conference::getSections).findFirst().orElse(null);
    }

    @Override
    public Conference save(Conference conference) {
        return conferenceRepository.save(conference);
    }

    @Override
    public void delete(Long conferenceId) {
        conferenceRepository.deleteById(conferenceId);
    }

    @Override
    @Transactional
    public Optional<Conference> postponeBiddingDeadline(Long conferenceId, Date newBiddingDate) {
        Conference conference = conferenceRepository.findById(conferenceId).orElse(null);
        if(conference == null)
            return Optional.empty();

        conference.setBiddingDeadline(newBiddingDate);
        return Optional.of(conference);
    }

    @Override
    @Transactional
    public Optional<Conference> postponeFullPaperDeadline(Long conferenceId, Date newFullPaperDate) {
        Conference conference = conferenceRepository.findById(conferenceId).orElse(null);
        if(conference == null)
            return Optional.empty();

        conference.setFullPaperDeadline(newFullPaperDate);
        return Optional.of(conference);
    }

    @Override
    @Transactional
    public Optional<Conference> postponeAbstractPaperDeadline(Long conferenceId, Date newAbstractDate) {
        Conference conference = conferenceRepository.findById(conferenceId).orElse(null);
        if(conference == null)
            return Optional.empty();

        conference.setAbstractPaperDeadline(newAbstractDate);
        return Optional.of(conference);
    }

    @Override
    @Transactional
    public Optional<Conference> postponeConference(Long conferenceId, Date newStartDate, Date newEndDate) {
        Conference conference = conferenceRepository.findById(conferenceId).orElse(null);
        if(conference == null)
            return Optional.empty();

        conference.setStartDate(newStartDate);
        conference.setEndDate(newEndDate);
        return Optional.of(conference);
    }
}
