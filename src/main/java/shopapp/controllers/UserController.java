package shopapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shopapp.dao.UserRepository;
import shopapp.models.UserEntity;
import shopapp.services.UserService;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/v1/spring/users/{id}")
    public UserEntity findUserByIdSpring(@PathVariable("id") int id){
        return userRepository.findUserEntityById(id);
    }

    @GetMapping(value = "/v1/spring/users")
    public List<UserEntity> findAllUsersSpring(){
        return userRepository.findAll();
    }

    @PostMapping(value = "/v1/spring/users")
    public void createUserSpring(@RequestBody UserEntity userEntity){
        userRepository.saveAndFlush(userEntity);
    }

    @PutMapping(value = "/v1/spring/users")
    public void updateUserSpring(@RequestBody UserEntity userEntity){
        UserEntity updatedUserEntity = userRepository.findUserEntityById(userEntity.getId());
        updatedUserEntity.setName(userEntity.getName());
        updatedUserEntity.setPassword(userEntity.getPassword());
        userRepository.saveAndFlush(updatedUserEntity);
    }

    @DeleteMapping(value = "/v1/spring/users/{id}")
    public void deleteUserSpring(@PathVariable("id")int id){
        UserEntity deletedUserEntity = userRepository.findUserEntityById(id);
        userRepository.delete(deletedUserEntity);
    }

    @GetMapping(value = "/v1/users/{id}")
    public UserEntity findUserById(@PathVariable("id") int id) {
        return userService.findUserById(id);
    }

    @GetMapping(value = "/v1/users")
    public List<UserEntity> findAllUsers() {
        return userService.findAllUsers();
    }

    @PostMapping(value = "/v1/users")
    public void createUser(@RequestBody UserEntity userEntity, HttpServletResponse response) {
        userService.createUser(userEntity);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    @PutMapping(value = "/v1/users")
    public void updateUser(@RequestBody UserEntity userEntity, HttpServletResponse response) {
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
