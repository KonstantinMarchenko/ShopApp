package shopapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shopapp.dao.UserRepository;
import shopapp.models.UsersEntity;

import java.util.List;

public interface SpringUserService {

    public UsersEntity findUserById(int id);

    public List<UsersEntity> findAllUsers();

    public void createUser(UsersEntity userEntity);

    public boolean updateUser(UsersEntity userEntity);

    public boolean deleteUserById(int id);
}
