package shopapp.services.spring;

import shopapp.models.UserEntity;

import java.util.List;

public interface SpringUserService {

    public UserEntity findUserById(int id);

    public List<UserEntity> findAllUsers();

    public UserEntity createUser(UserEntity userEntity);

    public boolean updateUser(UserEntity userEntity);

    public boolean deleteUserById(int id);
}
