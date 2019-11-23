package shopapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shopapp.dto.BasketCreateDto;
import shopapp.dto.BasketDto;
import shopapp.dto.BasketProductDto;
import shopapp.models.BasketEntity;
import shopapp.services.hibernate.BasketService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class BasketController {

    @Autowired
    BasketService basketService;

//    @GetMapping(value = "/v1/baskets/{id}")
//    public BasketsEntity findBasketById(@PathVariable("id") int id) {
//        return basketService.findBasketById(id);
//    }

    @GetMapping(value = "/v2/baskets/users/{userId}")
    public BasketDto findAllBasketsByUserId(@PathVariable("userId") int userId) {
        List<BasketEntity> basketsEntities = basketService.findAllBasketsByUserId(userId);
        return BasketDto.MapFrom(basketsEntities);
    }

    @PostMapping(value = "/v2/baskets")
    public void createBasket(@RequestBody BasketCreateDto basketCreateDto, HttpServletResponse response) {
        BasketEntity basket = basketCreateDto.MapTo();

        if (basketService.createBasket(basket)) {
            response.setStatus(HttpServletResponse.SC_CREATED);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @PutMapping(value = "/v2/baskets/{id}/{quantity}")
    public void updateBasket(@PathVariable int id, int quantity, HttpServletResponse response) {
        if (basketService.updateBasket(id, quantity)) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/v2/baskets/{id}")
    public void deleteBasketById(@PathVariable("id") int id, HttpServletResponse response) {
        if (basketService.deleteBasketById(id)) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
