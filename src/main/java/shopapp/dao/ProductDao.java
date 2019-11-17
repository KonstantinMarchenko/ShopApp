package shopapp.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import shopapp.models.ProductsEntity;
import shopapp.utils.HibernateSessionFactoryUtil;

import java.util.List;

@Repository
public class ProductDao {

    public ProductsEntity findProductById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        ProductsEntity productEntity = session.get(ProductsEntity.class, id);
        session.getTransaction().commit();
        session.close();
        return productEntity;
    }

    public List<ProductsEntity> findAllProducts() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<ProductsEntity> productEntities = (List<ProductsEntity>) session.createQuery("from ProductsEntity").list();
        session.getTransaction().commit();
        session.close();
        return productEntities;
    }

    public void createProduct(ProductsEntity productEntity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(productEntity);
        session.getTransaction().commit();
        session.close();
    }

    public boolean updateProduct(ProductsEntity productEntity) {
        boolean productPresent = false;

        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        ProductsEntity updatedProductEntity = session.get(ProductsEntity.class, productEntity.getId());

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
        ProductsEntity productEntity = session.get(ProductsEntity.class, id);

        if (productEntity != null) {
            porductPresent = true;
            session.delete(productEntity);
        }

        session.getTransaction().commit();
        session.close();

        return porductPresent;
    }
}
