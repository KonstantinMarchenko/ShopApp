package shopapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shopapp.dao.ProductRepository;
import shopapp.models.ProductsEntity;

import java.util.List;

@Service
public class SpringProductServiceImpl implements SpringProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public ProductsEntity findProductById(int id) {
        ProductsEntity productEntity = productRepository.findProductEntityById(id);
        return productEntity;
    }

    @Override
    public List<ProductsEntity> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void createProduct(ProductsEntity productEntity) {
        productRepository.saveAndFlush(productEntity);
    }

    @Override
    public boolean updateProduct(ProductsEntity productEntity) {

        boolean productEntityPresent = false;

        ProductsEntity updatedProductEntity = productRepository.findProductEntityById(productEntity.getId());

        if (updatedProductEntity != null){
            productEntityPresent = true;
            updatedProductEntity.setPrice(productEntity.getPrice());
            updatedProductEntity.setName(productEntity.getName());
            productRepository.saveAndFlush(updatedProductEntity);
        }

        return productEntityPresent;
    }

    @Override
    public boolean deleteProductById(int id) {

        boolean productEntityPresent = false;

        ProductsEntity deletedProductEntity = productRepository.findProductEntityById(id);

        if (deletedProductEntity != null){
            productEntityPresent = true;
            productRepository.delete(deletedProductEntity);
        }

        return productEntityPresent;
    }
}
