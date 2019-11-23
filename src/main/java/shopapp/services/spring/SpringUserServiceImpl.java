package shopapp.services.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shopapp.dao.spring.UserRepository;
import shopapp.models.UserEntity;

import java.util.List;

@Service
public class SpringUserServiceImpl implements SpringUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserEntity findUserById(int id) {
        return userRepository.findUserEntityById(id);
    }

    @Override
    public List<UserEntity> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity createUser(UserEntity userEntity) {
        return userRepository.saveAndFlush(userEntity);
    }

    @Override
    public boolean updateUser(UserEntity userEntity) {

        boolean userEntityPresent = false;

        UserEntity updatedUserEntity = userRepository.findUserEntityById(userEntity.getId());

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

        UserEntity deletedUserEntity = userRepository.findUserEntityById(id);

        if (deletedUserEntity != null) {
            userEntityPresent = true;
            userRepository.delete(deletedUserEntity);
        }

        return userEntityPresent;
    }
}
