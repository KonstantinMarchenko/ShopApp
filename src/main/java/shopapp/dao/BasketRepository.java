package shopapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shopapp.models.BasketEntity;

@Repository
public interface BasketRepository extends JpaRepository<BasketEntity, Long> {

    public BasketEntity findBasketEntityById(int id);
}
