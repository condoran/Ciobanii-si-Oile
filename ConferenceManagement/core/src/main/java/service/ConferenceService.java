package service;

import domain.Conference;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ConferenceService {

    Optional<Conference> getConferenceById(Long conferenceId);

    List<Conference> getAll();

    Conference save(Conference conference);

    void delete(Long conferenceId);

    Optional<Conference> postponeBiddingDeadline(Long conferenceId, Date newBiddingDate);

    Optional<Conference> postponeFullPaperDeadline(Long conferenceId, Date newFullPaperDate);

    Optional<Conference> postponeAbstractPaperDeadline(Long conferenceId, Date newAbstractDate);

    Optional<Conference> postponeConference(Long conferenceId, Date newStartDate, Date newEndDate);
}
