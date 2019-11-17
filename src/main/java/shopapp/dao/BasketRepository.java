package shopapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shopapp.models.BasketsEntity;

@Repository
public interface BasketRepository extends JpaRepository<BasketsEntity, Long> {

    public BasketsEntity findBasketEntityById(int id);
}
