package shopapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shopapp.dao.ProductDao;
import shopapp.models.ProductsEntity;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductDao productDao;

    public ProductsEntity findProductById(int id) {
        return productDao.findProductById(id);
    }

    public List<ProductsEntity> findAllProducts() {
        return productDao.findAllProducts();
    }

    public void createProduct(ProductsEntity productEntity) {
        productDao.createProduct(productEntity);
    }

    public boolean updateProduct(ProductsEntity productEntity) {
        return productDao.updateProduct(productEntity);
    }

    public boolean deleteProductById(int id) {
        return productDao.deleteProductById(id);
    }
}
