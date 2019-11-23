package shopapp.dto;

import org.apache.catalina.User;
import shopapp.models.BasketEntity;
import shopapp.models.ProductEntity;
import shopapp.models.UserEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BasketDto {
    private int userId;
    private String userName;
    private ArrayList<BasketProductDto> products = new ArrayList<BasketProductDto>();

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Collection<BasketProductDto> getProducts() {
        return products;
    }

    public static BasketDto MapFrom(List<BasketEntity> basketsEntities){
        BasketDto basketDto = new BasketDto();

        if (basketsEntities.size() == 0) return basketDto;

        UserEntity user = basketsEntities.get(0).getUserByUserId();
        basketDto.setUserId(user.getId());
        basketDto.setUserName(user.getName());

        for (BasketEntity basketsEntity: basketsEntities) {
            basketDto.products.add(BasketProductDto.MapFrom(basketsEntity));
        }

        return basketDto;
    }
//
//    public BasketEntity MapTo(){
//        BasketEntity basket = new BasketEntity();
//        ProductEntity product = new ProductEntity();
//        UserEntity user = new UserEntity();
//        user.setId(this.getUserId());
//        product.setId(this.products.get(0).getId());
//        product.setQuantity(this.products.get(0).getQuantity());
//        basket.setProductByProductId(product);
//        basket.setUserByUserId(user);
//        return basket;
//    }
}
