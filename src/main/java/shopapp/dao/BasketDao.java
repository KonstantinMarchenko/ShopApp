package shopapp.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import shopapp.models.BasketEntity;
import shopapp.utils.HibernateSessionFactoryUtil;

import java.util.List;

@Repository
public class BasketDao {

    public BasketEntity findBasketById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        BasketEntity basketEntity = session.get(BasketEntity.class, id);
        session.getTransaction().commit();
        session.close();
        return basketEntity;
    }

    public List<BasketEntity> findAllBaskets() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<BasketEntity> productEntities = (List<BasketEntity>) session.createQuery("from BasketEntity ").list();
        session.getTransaction().commit();
        session.close();
        return productEntities;
    }

    public void createBasket(BasketEntity basketEntity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(basketEntity);
        session.getTransaction().commit();
        session.close();
    }

    public boolean updateBasket(BasketEntity basketEntity) {
        boolean baskerPresent = false;

        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        BasketEntity updatedBasketEntity = session.get(BasketEntity.class, basketEntity.getId());

        if (updatedBasketEntity != null) {
            baskerPresent = true;
            updatedBasketEntity.setName(basketEntity.getName());
            session.update(updatedBasketEntity);
        }

        session.getTransaction().commit();
        session.close();

        return baskerPresent;
    }

    public boolean deleteBasketById(int id) {
        boolean basketPresent = false;

        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        BasketEntity basketEntity = session.get(BasketEntity.class, id);

        if (basketEntity != null) {
            basketPresent = true;
            session.delete(basketEntity);
        }

        session.getTransaction().commit();
        session.close();

        return basketPresent;
    }
}
