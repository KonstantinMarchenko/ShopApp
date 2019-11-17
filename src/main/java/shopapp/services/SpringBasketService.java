package shopapp.services;

import shopapp.models.BasketsEntity;

import java.util.List;

public interface SpringBasketService {

    public BasketsEntity findBasketById(int id);

    public List<BasketsEntity> findAllBaskets();

    public void createBasket(BasketsEntity basketEntity);

    public boolean updateBasket(BasketsEntity basketEntity);

    public boolean deleteBasketById(int id);
}
