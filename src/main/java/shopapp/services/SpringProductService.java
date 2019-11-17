package shopapp.services;

import shopapp.models.ProductsEntity;

import java.util.List;

public interface SpringProductService {

    public ProductsEntity findProductById(int id);

    public List<ProductsEntity> findAllProducts();

    public void createProduct(ProductsEntity productEntity);

    public boolean updateProduct(ProductsEntity productEntity);

    public boolean deleteProductById(int id);
}
