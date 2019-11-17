package shopapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shopapp.dao.BasketRepository;
import shopapp.models.BasketsEntity;

import java.util.List;

@Service
public class SpringBasketServiceImpl implements SpringBasketService{

    @Autowired
    BasketRepository basketRepository;

    @Override
    public BasketsEntity findBasketById(int id) {
        return basketRepository.findBasketEntityById(id);
    }

    @Override
    public List<BasketsEntity> findAllBaskets() {
        return basketRepository.findAll();
    }

    @Override
    public void createBasket(BasketsEntity basketEntity) {
        basketRepository.saveAndFlush(basketEntity);
    }

    @Override
    public boolean updateBasket(BasketsEntity basketEntity) {

        boolean basketEntityPresent = false;

        BasketsEntity updatedBasketEntity = basketRepository.findBasketEntityById(basketEntity.getId());

        if (updatedBasketEntity != null){
            basketEntityPresent = true;
            basketRepository.saveAndFlush(updatedBasketEntity);
        }

        return basketEntityPresent;
    }

    @Override
    public boolean deleteBasketById(int id) {

        boolean basketEntityPresent = false;

        BasketsEntity deletedBasketEntity = basketRepository.findBasketEntityById(id);

        if (deletedBasketEntity != null){
            basketEntityPresent = true;
            basketRepository.delete(deletedBasketEntity);
        }

        return basketEntityPresent;
    }
}
