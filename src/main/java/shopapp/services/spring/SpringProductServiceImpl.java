package shopapp.services.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shopapp.dao.spring.ProductRepository;
import shopapp.models.ProductEntity;

import java.util.List;

@Service
public class SpringProductServiceImpl implements SpringProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public ProductEntity findProductById(int id) {
        ProductEntity productEntity = productRepository.findProductEntityById(id);
        return productEntity;
    }

    @Override
    public List<ProductEntity> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public ProductEntity createProduct(ProductEntity productEntity) {
        return productRepository.saveAndFlush(productEntity);
    }

    @Override
    public boolean updateProduct(ProductEntity productEntity) {

        boolean productEntityPresent = false;

        ProductEntity updatedProductEntity = productRepository.findProductEntityById(productEntity.getId());

        if (updatedProductEntity != null){
            productEntityPresent = true;
            updatedProductEntity.setPrice(productEntity.getPrice());
            updatedProductEntity.setName(productEntity.getName());
            updatedProductEntity.setQuantity(productEntity.getQuantity());
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
