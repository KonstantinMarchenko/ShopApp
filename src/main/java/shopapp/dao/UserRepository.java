package shopapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import shopapp.models.UsersEntity;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UsersEntity, Long> {

    public UsersEntity findUserEntityById(int id);
}
