package shopapp.services.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shopapp.dao.hibernate.BasketDao;
import shopapp.models.BasketEntity;

import java.util.List;

@Service
public class BasketService {

    @Autowired
    BasketDao basketDao;

//    public BasketEntity findBasketById(int id) {
//        return basketDao.findBasketById(id);
//    }

    public List<BasketEntity> findAllBasketsByUserId(int id) {
        return basketDao.findAllBasketsByUserId(id);
    }

    public boolean createBasket(BasketEntity basketEntity) {
        return basketDao.createBasket(basketEntity);
    }

    public boolean updateBasket(int id, int quantity) {
        return basketDao.updateBasket(id, quantity);
    }

    public boolean deleteBasketById(int id) {
        return basketDao.deleteBasketById(id);
    }
}
