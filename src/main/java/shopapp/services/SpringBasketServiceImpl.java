package shopapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shopapp.dao.BasketRepository;
import shopapp.models.BasketEntity;

import java.util.List;

@Service
public class SpringBasketServiceImpl implements SpringBasketService{

    @Autowired
    BasketRepository basketRepository;

    @Override
    public BasketEntity findBasketById(int id) {
        return basketRepository.findBasketEntityById(id);
    }

    @Override
    public List<BasketEntity> findAllBaskets() {
        return basketRepository.findAll();
    }

    @Override
    public void createBasket(BasketEntity basketEntity) {
        basketRepository.saveAndFlush(basketEntity);
    }

    @Override
    public boolean updateBasket(BasketEntity basketEntity) {

        boolean basketEntityPresent = false;

        BasketEntity updatedBasketEntity = basketRepository.findBasketEntityById(basketEntity.getId());

        if (updatedBasketEntity != null){
            basketEntityPresent = true;
            updatedBasketEntity.setName(basketEntity.getName());
            basketRepository.saveAndFlush(updatedBasketEntity);
        }

        return basketEntityPresent;
    }

    @Override
    public boolean deleteBasketById(int id) {

        boolean basketEntityPresent = false;

        BasketEntity deletedBasketEntity = basketRepository.findBasketEntityById(id);

        if (deletedBasketEntity != null){
            basketEntityPresent = true;
            basketRepository.delete(deletedBasketEntity);
        }

        return basketEntityPresent;
    }
}
