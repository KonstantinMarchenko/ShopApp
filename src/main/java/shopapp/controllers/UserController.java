package shopapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shopapp.dto.UserDto;
import shopapp.models.UserEntity;
import shopapp.services.hibernate.UserService;
import shopapp.services.spring.SpringUserService;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    SpringUserService userService;

    @Autowired
    UserService userServiceHibernate;

    @GetMapping(value = "/v2/users/{id}")
    public UserDto findUserById(@PathVariable("id") int id, HttpServletResponse response) {
        UserEntity usersEntity = userService.findUserById(id);

        if (usersEntity == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }

        UserDto userDto = UserDto.MapFrom(usersEntity);
        return userDto;
    }

    @GetMapping(value = "/v2/users")
    public List<UserDto> findAllUsers() {
        List<UserEntity> usersEntityList = userService.findAllUsers();
        List<UserDto> userDtoList = new ArrayList<UserDto>();

        for (UserEntity user: usersEntityList) {
            userDtoList.add(UserDto.MapFrom(user));
        }

        return userDtoList;
    }

    @PostMapping(value = "/v2/users")
    public UserDto createUser(@RequestBody UserDto userDto, HttpServletResponse response) {
        UserEntity user = userDto.MapTo();
        user = userService.createUser(user);
        userDto = UserDto.MapFrom(user);
        response.setStatus(HttpServletResponse.SC_CREATED);
        return userDto;
    }

    @PutMapping(value = "/v2/users")
    public UserDto updateUser(@RequestBody UserDto userDto, HttpServletResponse response) {
        UserEntity userEntity = userDto.MapTo();
        userEntity.setId(userDto.getId());

        if (userService.updateUser(userEntity)) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        userDto = UserDto.MapFrom(userEntity);
        return userDto;
    }

    @DeleteMapping(value = "/v2/users/{id}")
    public void deleteUserById(@PathVariable("id") int id, HttpServletResponse response) {
        if (userServiceHibernate.deleteUserById(id)) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
