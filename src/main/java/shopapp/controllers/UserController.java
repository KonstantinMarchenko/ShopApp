package shopapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shopapp.models.UsersEntity;
import shopapp.services.SpringUserService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    SpringUserService userService;

    @GetMapping(value = "/v1/users/{id}")
    public UsersEntity findUserById(@PathVariable("id") int id) {
        return userService.findUserById(id);
    }

    @GetMapping(value = "/v1/users")
    public List<UsersEntity> findAllUsers() {
        return userService.findAllUsers();
    }

    @PostMapping(value = "/v1/users")
    public void createUser(@RequestBody UsersEntity userEntity, HttpServletResponse response) {
        userService.createUser(userEntity);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    @PutMapping(value = "/v1/users")
    public void updateUser(@RequestBody UsersEntity userEntity, HttpServletResponse response) {
        if (userService.updateUser(userEntity)) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

    }

    @DeleteMapping(value = "/v1/users/{id}")
    public void deleteUserById(@PathVariable("id") int id, HttpServletResponse response) {
        if (userService.deleteUserById(id)) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
