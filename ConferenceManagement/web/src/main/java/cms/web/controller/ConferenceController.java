package cms.web.controller;

import cms.core.domain.CMSUser;
import cms.core.domain.Conference;
import cms.core.service.ConferenceService;
import cms.web.converter.ConferenceConverter;
import cms.web.converter.UserConverter;
import cms.web.dto.ConferenceDTO;
import cms.web.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ConferenceController {
    @Autowired
    private ConferenceService conferenceService;

    @Autowired
    private ConferenceConverter conferenceConverter;

    @Autowired
    private UserConverter userConverter;

    @RequestMapping(value = "conference/getConferences", method = RequestMethod.GET)
    List<ConferenceDTO> getConferences(){
        List<Conference> conferences = conferenceService.getAll();
        return new ArrayList<>(conferenceConverter.convertModelsToDtos(conferences));
    }

    @RequestMapping(value = "conference/getConferencePCMembers", method = RequestMethod.GET)
    List<UserDTO> getConferencePCMembers(@RequestParam Long conferenceID){
        List<CMSUser> PCMembers = conferenceService.getPCMembersForConference(conferenceID);
        return new ArrayList<>(userConverter.convertModelsToDtos(PCMembers));
    }

    @RequestMapping(value = "conference/postponeConference", method = RequestMethod.POST)
    ConferenceDTO postponeConference(@RequestBody ConferenceDTO conferenceDTO){
        Optional<Conference> updatedConference =
                conferenceService.postponeConference(conferenceDTO.getId(), conferenceDTO.getStartDate(), conferenceDTO.getEndDate());

        if(updatedConference.isEmpty()){
            return null;
        }
        return conferenceConverter.convertModelToDto(updatedConference.get());
    }
}
