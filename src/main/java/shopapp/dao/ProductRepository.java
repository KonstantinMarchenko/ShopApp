package shopapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shopapp.models.ProductsEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductsEntity, Long> {

    public ProductsEntity findProductEntityById(int id);
}
