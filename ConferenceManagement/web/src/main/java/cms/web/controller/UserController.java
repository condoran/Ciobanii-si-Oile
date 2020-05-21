package cms.web.controller;

import cms.core.domain.CMSUser;
import cms.core.service.UserService;
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
public class UserController {
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
        return userConverter.convertModelToDto(userService.save(userConverter.convertDtoToModel(userDTO)));
    }

    @RequestMapping(value = "/user/checkUser", method = RequestMethod.POST)
    UserDTO checkUser(@RequestBody String[] args){
        String username = args[0];
        String password = args[1];
        Optional<CMSUser> cmsUser = userService.getUserByUsername(username);
        if(cmsUser.isEmpty() || !cmsUser.get().getPassword().equals(password)){
            return null;
        }
        return userConverter.convertModelToDto(cmsUser.get());
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
