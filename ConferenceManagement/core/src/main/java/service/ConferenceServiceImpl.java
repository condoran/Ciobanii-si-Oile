package service;

import domain.Conference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repo.ConferenceRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ConferenceServiceImpl implements ConferenceService {
    @Autowired
    private ConferenceRepository conferenceRepository;

    @Override
    public List<Conference> getAll() {
        return conferenceRepository.findAll();
    }

    @Override
    public Conference save(Conference conference) {
        return conferenceRepository.save(conference);
    }

    @Override
    public void delete(Long id) {
        conferenceRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<Conference> postponeBiddingDeadline(Long id, Date newBiddingDate) {
        Conference conference = conferenceRepository.findById(id).orElse(null);
        if(conference == null)
            return Optional.empty();

        conference.setBiddingDeadline(newBiddingDate);
        return Optional.of(conference);
    }

    @Override
    @Transactional
    public Optional<Conference> postponeFullPaperDeadline(Long id, Date newFullPaperDate) {
        Conference conference = conferenceRepository.findById(id).orElse(null);
        if(conference == null)
            return Optional.empty();

        conference.setFullPaperDeadline(newFullPaperDate);
        return Optional.of(conference);
    }

    @Override
    @Transactional
    public Optional<Conference> postponeAbstractPaperDeadline(Long id, Date newAbstractDate) {
        Conference conference = conferenceRepository.findById(id).orElse(null);
        if(conference == null)
            return Optional.empty();

        conference.setAbstractPaperDeadline(newAbstractDate);
        return Optional.of(conference);
    }

    @Override
    @Transactional
    public Optional<Conference> postponeConference(Long id, Date newStartDate, Date newEndDate) {
        Conference conference = conferenceRepository.findById(id).orElse(null);
        if(conference == null)
            return Optional.empty();

        conference.setStartDate(newStartDate);
        conference.setEndDate(newEndDate);
        return Optional.of(conference);
    }
}
