package service;

import domain.Conference;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ConferenceService {
    List<Conference> getAll();

    Conference save(Conference conference);

    void delete(Long id);

    Optional<Conference> postponeBiddingDeadline(Long id, Date newBiddingDate);

    Optional<Conference> postponeFullPaperDeadline(Long id, Date newFullPaperDate);

    Optional<Conference> postponeAbstractPaperDeadline(Long id, Date newAbstractDate);

    Optional<Conference> postponeConference(Long id, Date newStartDate, Date newEndDate);
}
