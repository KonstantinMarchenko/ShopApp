package shopapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shopapp.dao.UserRepository;
import shopapp.models.UserEntity;

import java.util.List;

public interface SpringUserService {

    public UserEntity findUserById(int id);

    public List<UserEntity> findAllUsers();

    public void createUser(UserEntity userEntity);

    public boolean updateUser(UserEntity userEntity);

    public boolean deleteUserById(int id);
}
