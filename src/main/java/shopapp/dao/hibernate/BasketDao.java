package shopapp.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import shopapp.models.BasketEntity;
import shopapp.models.ProductEntity;
import shopapp.utils.HibernateSessionFactoryUtil;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BasketDao {

//    public BasketEntity findBasketById(int id) {
//        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
//        session.beginTransaction();
//        BasketEntity basketEntity = session.get(BasketEntity.class, id);
//        session.getTransaction().commit();
//        session.close();
//        return basketEntity;
//    }

    public List<BasketEntity> findAllBasketsByUserId(int id) {
        List<BasketEntity> productEntities = new ArrayList<BasketEntity>();
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from BasketEntity b where b.userId =: userId");
            query.setParameter("userId", id);
            productEntities = (List<BasketEntity>) query.list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            session.close();
            return productEntities;
        }
    }

    public boolean createBasket(BasketEntity basketEntity) {
        boolean checkQuantity = false;

        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            int userCreatedId = basketEntity.getUserByUserId().getId();
            int productCreatedId = basketEntity.getProductByProductId().getId();
            int basketCreatedQuantity = basketEntity.getQuantity();
            ProductEntity productEntity = session.get(ProductEntity.class, productCreatedId);
            int productQuantity = productEntity.getQuantity();
            Query query = session.createQuery("from BasketEntity b where b.userId =: userId and b.productId =: productId")
                    .setParameter("userId", userCreatedId)
                    .setParameter("productId", productCreatedId);

            List<BasketEntity> currentBasketEntity = (List<BasketEntity>) query.list();

            if (currentBasketEntity.size() == 0) {
                if (productQuantity >= basketCreatedQuantity) {
                    checkQuantity = true;
                    productEntity.setQuantity(productQuantity - basketCreatedQuantity);
                    session.update(productEntity);
                    session.save(basketEntity);
                }
            } else {
                int currentBasketQuantity = currentBasketEntity.get(0).getQuantity();

                if (productQuantity >= basketCreatedQuantity) {
                    checkQuantity = true;
                    productEntity.setQuantity(productQuantity - basketCreatedQuantity);
                    session.update(productEntity);
                    currentBasketEntity.get(0).setQuantity(currentBasketQuantity + basketCreatedQuantity);
                    session.update(currentBasketEntity.get(0));
                }
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            session.close();
            return checkQuantity;
        }
    }

    public boolean updateBasket(int id, int quantity) {
        boolean basketPresent = false;
        boolean checkQuantity = false;

        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            BasketEntity basketEntity = session.get(BasketEntity.class, id);
            int basketQuantity = basketEntity.getQuantity();

            if (basketEntity != null) {
                basketPresent = true;
                ProductEntity productEntity = basketEntity.getProductByProductId();
                int productQuantity = productEntity.getQuantity();

                if (productQuantity >= (quantity - basketQuantity)) {
                    checkQuantity = true;
                    productEntity.setQuantity(productQuantity - (quantity - basketQuantity));
                    basketEntity.setQuantity(quantity);
                    session.update(productEntity);
                    session.update(basketEntity);
                }
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            session.close();
            return (basketPresent && checkQuantity);
        }
    }

    public boolean deleteBasketById(int id) {
        boolean basketPresent = false;

        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            BasketEntity basketEntity = session.get(BasketEntity.class, id);

            if (basketEntity != null) {
                basketPresent = true;
                int basketQuantity = basketEntity.getQuantity();
                int productId = basketEntity.getProductId();
                ProductEntity productEntity = session.get(ProductEntity.class, productId);
                int productQuantity = productEntity.getQuantity();
                productEntity.setQuantity(productQuantity + basketQuantity);
                session.update(productEntity);
                session.delete(basketEntity);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            session.close();
            return basketPresent;
        }
    }
}
