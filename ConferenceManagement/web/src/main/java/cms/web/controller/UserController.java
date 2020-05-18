package cms.web.controller;

import cms.core.domain.CMSUser;
import cms.core.service.UserService;
import cms.web.converter.UserConverter;
import cms.web.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserConverter userConverter;

    @RequestMapping(value = "/users/all", method = RequestMethod.GET)
    List<UserDTO> getUsers(){
        List<CMSUser> users = userService.getAllUsers();
        return new ArrayList<>(userConverter.convertModelsToDtos(users));
    }
    
    @RequestMapping(value = "/users/save", method = RequestMethod.POST)
    UserDTO saveUser(@RequestBody UserDTO userDTO){
        return userConverter.convertModelToDto(userService.save(userConverter.convertDtoToModel(userDTO)));
    }
}
