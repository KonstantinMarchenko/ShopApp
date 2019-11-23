package shopapp.dao.hibernate;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import shopapp.models.ProductEntity;
import shopapp.utils.HibernateSessionFactoryUtil;

import java.util.List;

@Repository
public class ProductDao {

    public ProductEntity findProductById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        ProductEntity productEntity = session.get(ProductEntity.class, id);
        session.getTransaction().commit();
        session.close();
        return productEntity;
    }

    public List<ProductEntity> findAllProducts() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<ProductEntity> productEntities = (List<ProductEntity>) session.createQuery("from ProductEntity").list();
        session.getTransaction().commit();
        session.close();
        return productEntities;
    }

    public void createProduct(ProductEntity productEntity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(productEntity);
        session.getTransaction().commit();
        session.close();
    }

    public boolean updateProduct(ProductEntity productEntity) {
        boolean productPresent = false;

        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        ProductEntity updatedProductEntity = session.get(ProductEntity.class, productEntity.getId());

        if (updatedProductEntity != null) {
            productPresent = true;
            updatedProductEntity.setName(productEntity.getName());
            updatedProductEntity.setPrice(productEntity.getPrice());
            session.update(updatedProductEntity);
        }

        session.getTransaction().commit();
        session.close();

        return productPresent;
    }

    public boolean deleteProductById(int id) {
        boolean porductPresent = false;

        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        ProductEntity productEntity = session.get(ProductEntity.class, id);

        if (productEntity != null) {
            porductPresent = true;
            session.delete(productEntity);
        }

        session.getTransaction().commit();
        session.close();

        return porductPresent;
    }
}
