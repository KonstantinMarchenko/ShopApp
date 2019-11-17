package shopapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shopapp.dao.UserRepository;
import shopapp.models.UsersEntity;

import java.util.List;

@Service
public class SpringUserServiceImpl implements SpringUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UsersEntity findUserById(int id) {
        return userRepository.findUserEntityById(id);
    }

    @Override
    public List<UsersEntity> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void createUser(UsersEntity userEntity) {
        userRepository.saveAndFlush(userEntity);
    }

    @Override
    public boolean updateUser(UsersEntity userEntity) {

        boolean userEntityPresent = false;

        UsersEntity updatedUserEntity = userRepository.findUserEntityById(userEntity.getId());

        if (updatedUserEntity != null) {
            userEntityPresent = true;
            updatedUserEntity.setName(userEntity.getName());
            updatedUserEntity.setPassword(userEntity.getPassword());
            userRepository.saveAndFlush(updatedUserEntity);
        }

        return userEntityPresent;
    }

    @Override
    public boolean deleteUserById(int id) {

        boolean userEntityPresent = false;

        UsersEntity deletedUserEntity = userRepository.findUserEntityById(id);

        if (deletedUserEntity != null) {
            userEntityPresent = true;
            userRepository.delete(deletedUserEntity);
        }

        return userEntityPresent;
    }
}
