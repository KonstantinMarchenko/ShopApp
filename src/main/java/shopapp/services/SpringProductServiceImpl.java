package shopapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shopapp.dao.ProductRepository;
import shopapp.models.ProductEntity;

import java.util.List;

@Service
public class SpringProductServiceImpl implements SpringProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public ProductEntity findProductById(int id) {
        return productRepository.findProductEntityById(id);
    }

    @Override
    public List<ProductEntity> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void createProduct(ProductEntity productEntity) {
        productRepository.saveAndFlush(productEntity);
    }

    @Override
    public boolean updateProduct(ProductEntity productEntity) {

        boolean productEntityPresent = false;

        ProductEntity updatedProductEntity = productRepository.findProductEntityById(productEntity.getId());

        if (updatedProductEntity != null){
            productEntityPresent = true;
            updatedProductEntity.setQuantity(productEntity.getQuantity());
            updatedProductEntity.setName(productEntity.getName());
            productRepository.saveAndFlush(updatedProductEntity);
        }

        return productEntityPresent;
    }

    @Override
    public boolean deleteProductById(int id) {

        boolean productEntityPresent = false;

        ProductEntity deletedProductEntity = productRepository.findProductEntityById(id);

        if (deletedProductEntity != null){
            productEntityPresent = true;
            productRepository.delete(deletedProductEntity);
        }

        return productEntityPresent;
    }
}
