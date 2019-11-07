package shopapp.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import shopapp.models.UserEntity;
import shopapp.utils.HibernateSessionFactoryUtil;

import java.util.List;


@Repository
public class UserDao {

    public UserEntity findUserById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        UserEntity userEntity = session.get(UserEntity.class, id);
        session.getTransaction().commit();
        session.close();
        return userEntity;
    }

    public List<UserEntity> findAllUsers() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<UserEntity> userEntities = (List<UserEntity>) session.createQuery("from UserEntity ").list();
        session.getTransaction().commit();
        session.close();
        return userEntities;
    }

    public void createUser(UserEntity userEntity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(userEntity);
        session.getTransaction().commit();
        session.close();
    }

    public boolean updateUser(UserEntity userEntity) {
        boolean userPresent = false;

        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        UserEntity updatedUserEntity = session.get(UserEntity.class, userEntity.getId());

        if (updatedUserEntity != null) {
            userPresent = true;
            updatedUserEntity.setName(userEntity.getName());
            updatedUserEntity.setPassword(userEntity.getPassword());
            session.update(updatedUserEntity);
        }

        session.getTransaction().commit();
        session.close();

        return userPresent;
    }

    public boolean deleteUserById(int id) {
        boolean userPresent = false;

        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        UserEntity userEntity = session.get(UserEntity.class, id);

        if (userEntity != null) {
            userPresent = true;
            session.delete(userEntity);
        }

        session.getTransaction().commit();
        session.close();

        return userPresent;
    }
}
