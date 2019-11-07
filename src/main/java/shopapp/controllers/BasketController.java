package shopapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shopapp.models.BasketEntity;
import shopapp.services.BasketService;
import shopapp.services.SpringBasketService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class BasketController {

    @Autowired
    SpringBasketService basketService;

    @GetMapping(value = "/v1/baskets/{id}")
    public BasketEntity findBasketById(@PathVariable("id") int id) {
        return basketService.findBasketById(id);
    }

    @GetMapping(value = "/v1/baskets")
    public List<BasketEntity> findAllBaskets() {
        return basketService.findAllBaskets();
    }

    @PostMapping(value = "/v1/baskets")
    public void createBasket(@RequestBody BasketEntity basketEntity, HttpServletResponse response) {
        basketService.createBasket(basketEntity);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    @PutMapping(value = "/v1/baskets")
    public void updateBasket(@RequestBody BasketEntity basketEntity, HttpServletResponse response) {
        if (basketService.updateBasket(basketEntity)) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/v1/baskets/{id}")
    public void deleteBasketById(@PathVariable("id") int id, HttpServletResponse response) {
        if (basketService.deleteBasketById(id)) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
