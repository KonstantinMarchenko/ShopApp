package shopapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shopapp.dao.BasketDao;
import shopapp.models.BasketsEntity;

import java.util.List;

@Service
public class BasketService {

    @Autowired
    BasketDao basketDao;

    public BasketsEntity findBasketById(int id) {
        return basketDao.findBasketById(id);
    }

    public List<BasketsEntity> findAllBaskets() {
        return basketDao.findAllBaskets();
    }

    public void createBasket(BasketsEntity basketEntity) {
        basketDao.createBasket(basketEntity);
    }

    public boolean updateBasket(BasketsEntity basketEntity) {
        return basketDao.updateBasket(basketEntity);
    }

    public boolean deleteBasketById(int id) {
        return basketDao.deleteBasketById(id);
    }
}
