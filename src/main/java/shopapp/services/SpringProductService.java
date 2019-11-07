package shopapp.services;

import shopapp.models.ProductEntity;

import java.util.List;

public interface SpringProductService {

    public ProductEntity findProductById(int id);

    public List<ProductEntity> findAllProducts();

    public void createProduct(ProductEntity productEntity);

    public boolean updateProduct(ProductEntity productEntity);

    public boolean deleteProductById(int id);
}
