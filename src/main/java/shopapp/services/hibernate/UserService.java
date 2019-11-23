package shopapp.services.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shopapp.dao.hibernate.UserDao;
import shopapp.models.UserEntity;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public UserEntity findUserById(int id) {
        return userDao.findUserById(id);
    }

    public List<UserEntity> findAllUsers() {
        return userDao.findAllUsers();
    }

    public void createUser(UserEntity userEntity) {
        userDao.createUser(userEntity);
    }

    public boolean updateUser(UserEntity userEntity) {
        return userDao.updateUser(userEntity);
    }

    public boolean deleteUserById(int id) {
        return userDao.deleteUserById(id);
    }
}
