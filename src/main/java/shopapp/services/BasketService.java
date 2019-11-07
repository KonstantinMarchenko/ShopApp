package shopapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shopapp.dao.BasketDao;
import shopapp.models.BasketEntity;

import java.util.List;

@Service
public class BasketService {

    @Autowired
    BasketDao basketDao;

    public BasketEntity findBasketById(int id) {
        return basketDao.findBasketById(id);
    }

    public List<BasketEntity> findAllBaskets() {
        return basketDao.findAllBaskets();
    }

    public void createBasket(BasketEntity basketEntity) {
        basketDao.createBasket(basketEntity);
    }

    public boolean updateBasket(BasketEntity basketEntity) {
        return basketDao.updateBasket(basketEntity);
    }

    public boolean deleteBasketById(int id) {
        return basketDao.deleteBasketById(id);
    }
}
