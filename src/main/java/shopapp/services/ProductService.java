package shopapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shopapp.dao.ProductDao;
import shopapp.models.ProductEntity;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductDao productDao;

    public ProductEntity findProductById(int id) {
        return productDao.findProductById(id);
    }

    public List<ProductEntity> findAllProducts() {
        return productDao.findAllProducts();
    }

    public void createProduct(ProductEntity productEntity) {
        productDao.createProduct(productEntity);
    }

    public boolean updateProduct(ProductEntity productEntity) {
        return productDao.updateProduct(productEntity);
    }

    public boolean deleteProductById(int id) {
        return productDao.deleteProductById(id);
    }
}
