package shopapp.services.spring;

import shopapp.models.BasketEntity;

import java.util.List;

public interface SpringBasketService {

    public BasketEntity findBasketById(int id);

    public List<BasketEntity> findAllBaskets();

    public void createBasket(BasketEntity basketEntity);

    public boolean updateBasket(BasketEntity basketEntity);

    public boolean deleteBasketById(int id);
}
