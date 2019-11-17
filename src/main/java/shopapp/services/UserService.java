package shopapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shopapp.dao.UserDao;
import shopapp.models.UsersEntity;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public UsersEntity findUserById(int id) {
        return userDao.findUserById(id);
    }

    public List<UsersEntity> findAllUsers() {
        return userDao.findAllUsers();
    }

    public void createUser(UsersEntity userEntity) {
        userDao.createUser(userEntity);
    }

    public boolean updateUser(UsersEntity userEntity) {
        return userDao.updateUser(userEntity);
    }

    public boolean deleteUserById(int id) {
        return userDao.deleteUserById(id);
    }
}
