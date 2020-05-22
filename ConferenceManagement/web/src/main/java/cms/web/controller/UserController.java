package cms.web.controller;

import cms.core.domain.CMSUser;
import cms.core.service.UserService;
import cms.web.converter.ConferenceConverter;
import cms.web.converter.UserConverter;
import cms.web.dto.ConferenceDTO;
import cms.web.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    public static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private ConferenceConverter conferenceConverter;

    @RequestMapping(value = "/user/getUsers", method = RequestMethod.GET)
    List<UserDTO> getUsers(){
        List<CMSUser> users = userService.getAllUsers();
        return new ArrayList<>(userConverter.convertModelsToDtos(users));
    }

    @RequestMapping(value = "/user/getUsersByIDs", method = RequestMethod.POST)
    List<UserDTO> getUsersByIDs(@RequestBody List<Long> usersIDs){
        List<CMSUser> users = userService.getUsersByIDs(usersIDs);
        return new ArrayList<>(userConverter.convertModelsToDtos(users));
    }
    
    @RequestMapping(value = "/user/saveUser", method = RequestMethod.POST)
    UserDTO saveUser(@RequestBody UserDTO userDTO){
        logger.trace("saveUser Controller -method entered userDTO{}", userDTO);
        Optional<CMSUser> user1 = userService.getUserByUsername(userDTO.getUsername());
        Optional<CMSUser> user2 = userService.getUserByEmailAddress(userDTO.getEmailAddress());
        if(user1.isPresent() || user2.isPresent()){
            return null;
        }
        return userConverter.convertModelToDto(userService.save(userConverter.convertDtoToModel(userDTO)));
    }

    @RequestMapping(value = "/user/checkUser", method = RequestMethod.POST)
    boolean checkUser(@RequestBody String[] args){
        String username = args[0];
        String password = args[1];
        Optional<CMSUser> cmsUser = userService.getUserByUsername(username);
        return cmsUser.isPresent() && cmsUser.get().getPassword().equals(password);
    }

    @RequestMapping(value = "/user/getConferencesForPCMember", method = RequestMethod.POST)
    List<ConferenceDTO> getConferencesForPCMember(@RequestBody String username){
        return conferenceConverter.convertModelsToDtos(userService.getConferencesForPCMember(username));
    }

    @RequestMapping(value = "/user/getConferencesForPCMember2", method = RequestMethod.GET)
    List<ConferenceDTO> getConferencesForPCMember2(@RequestParam String username){
        return conferenceConverter.convertModelsToDtos(userService.getConferencesForPCMember(username));
    }

}
