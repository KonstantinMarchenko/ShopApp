package shopapp.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import shopapp.models.BasketsEntity;
import shopapp.utils.HibernateSessionFactoryUtil;

import java.util.List;

@Repository
public class BasketDao {

    public BasketsEntity findBasketById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        BasketsEntity basketEntity = session.get(BasketsEntity.class, id);
        session.getTransaction().commit();
        session.close();
        return basketEntity;
    }

    public List<BasketsEntity> findAllBaskets() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<BasketsEntity> productEntities = (List<BasketsEntity>) session.createQuery("from BasketsEntity ").list();
        session.getTransaction().commit();
        session.close();
        return productEntities;
    }

    public void createBasket(BasketsEntity basketEntity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(basketEntity);
        session.getTransaction().commit();
        session.close();
    }

    public boolean updateBasket(BasketsEntity basketEntity) {
        boolean baskerPresent = false;

        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        BasketsEntity updatedBasketEntity = session.get(BasketsEntity.class, basketEntity.getId());

        if (updatedBasketEntity != null) {
            baskerPresent = true;
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
        BasketsEntity basketEntity = session.get(BasketsEntity.class, id);

        if (basketEntity != null) {
            basketPresent = true;
            session.delete(basketEntity);
        }

        session.getTransaction().commit();
        session.close();

        return basketPresent;
    }
}
