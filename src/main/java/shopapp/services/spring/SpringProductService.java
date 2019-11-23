package shopapp.services.spring;

import shopapp.models.ProductEntity;

import java.util.List;

public interface SpringProductService {

    public ProductEntity findProductById(int id);

    public List<ProductEntity> findAllProducts();

    public ProductEntity createProduct(ProductEntity productEntity);

    public boolean updateProduct(ProductEntity productEntity);

    public boolean deleteProductById(int id);
}
