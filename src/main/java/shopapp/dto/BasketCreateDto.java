package shopapp.dto;

import shopapp.models.BasketEntity;
import shopapp.models.ProductEntity;
import shopapp.models.UserEntity;

public class BasketCreateDto {
    private int userId;
    private int productId;
    private int quantity;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BasketEntity MapTo() {
        BasketEntity basketEntity = new BasketEntity();
        ProductEntity productEntity = new ProductEntity();
        UserEntity userEntity = new UserEntity();
        userEntity.setId(this.getUserId());
        productEntity.setId(this.getProductId());
        basketEntity.setQuantity(this.getQuantity());
        basketEntity.setUserByUserId(userEntity);
        basketEntity.setProductByProductId(productEntity);
        return basketEntity;
    }
}
