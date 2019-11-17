package shopapp.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import shopapp.models.UsersEntity;
import shopapp.utils.HibernateSessionFactoryUtil;

import java.util.List;


@Repository
public class UserDao {

    public UsersEntity findUserById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        UsersEntity userEntity = session.get(UsersEntity.class, id);
        session.getTransaction().commit();
        session.close();
        return userEntity;
    }

    public List<UsersEntity> findAllUsers() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<UsersEntity> userEntities = (List<UsersEntity>) session.createQuery("from UsersEntity ").list();
        session.getTransaction().commit();
        session.close();
        return userEntities;
    }

    public void createUser(UsersEntity userEntity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(userEntity);
        session.getTransaction().commit();
        session.close();
    }

    public boolean updateUser(UsersEntity userEntity) {
        boolean userPresent = false;

        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        UsersEntity updatedUserEntity = session.get(UsersEntity.class, userEntity.getId());

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
        UsersEntity userEntity = session.get(UsersEntity.class, id);

        if (userEntity != null) {
            userPresent = true;
            session.delete(userEntity);
        }

        session.getTransaction().commit();
        session.close();

        return userPresent;
    }
}
