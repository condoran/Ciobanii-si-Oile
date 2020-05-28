package cms.web.controller;

import cms.core.domain.CMSUser;
import cms.core.domain.Section;
import cms.core.service.SectionService;
import cms.core.service.UserService;
import cms.web.converter.SectionConverter;
import cms.web.converter.UserConverter;
import cms.web.dto.SectionDTO;
import cms.web.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class SectionController {
    public static final Logger logger = LoggerFactory.getLogger(ConferenceController.class);

    @Autowired
    private SectionService sectionService;

    @Autowired
    private UserService userService;

    @Autowired
    private SectionConverter sectionConverter;

    @Autowired
    private UserConverter userConverter;

    @RequestMapping(value = "section/saveSection", method = RequestMethod.POST)
    SectionDTO saveSection(@RequestBody SectionDTO sectionDTO){
        logger.trace("saveSection, sectionDTO={}", sectionDTO);
        Section section = sectionService.save(sectionConverter.convertDtoToModel(sectionDTO));
        logger.trace("saveSection, saved section={}", section);

        return sectionConverter.convertModelToDto(section);
    }

    @RequestMapping(value = "section/getSectionByID", method = RequestMethod.POST)
    SectionDTO getSectionByID(@RequestBody Long sectionID){
        Optional<Section> section = sectionService.getSectionById(sectionID);
        return sectionConverter.convertModelToDto(section.get());
    }

    @RequestMapping(value = "section/getSectionsByIDs", method = RequestMethod.POST)
    List<SectionDTO> getSectionsByIDs(@RequestBody List<Long> sectionsIDs){
        List<Section> sections = sectionService.getSectionsByIDs(sectionsIDs);
        return new ArrayList<>(sectionConverter.convertModelsToDtos(sections));
    }

    @RequestMapping(value = "section/getSectionsForConference", method = RequestMethod.POST)
    List<SectionDTO> getSectionsForConference(@RequestBody Long conferenceID){
        List<Section> sections = sectionService.getAllByConferenceId(conferenceID);
        return new ArrayList<>(sectionConverter.convertModelsToDtos(sections));
    }

    @RequestMapping(value = "section/updateSectionChair/{sectionID}", method = RequestMethod.POST)
    SectionDTO updateSectionChair(@PathVariable Long sectionID, @RequestBody UserDTO userDTO){
        Optional<Section> section = sectionService.updateSectionChair(sectionID, userConverter.convertDtoToModel(userDTO));
        if(section.isEmpty()){
            return null;
        }
        return sectionConverter.convertModelToDto(section.get());
    }

    @RequestMapping(value = "section/getCandidatesForSectionChair", method = RequestMethod.POST)
    List<UserDTO> getCandidatesForSectionChair(@RequestBody Long conferenceID){
        logger.trace("in SectionController, getCandidatesForSectionChair, conferenceID = {}", conferenceID);
        List<CMSUser> candidates = userService.getCandidatesForSectionChair(conferenceID);
        logger.trace("in SectionController, getCandidatesForSectionChair, candidates = {}", candidates);
        return new ArrayList<>(userConverter.convertModelsToDtos(candidates));
    }


}
