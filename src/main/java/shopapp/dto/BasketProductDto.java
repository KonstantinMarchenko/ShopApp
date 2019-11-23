package shopapp.dto;

import shopapp.models.BasketEntity;
import shopapp.models.ProductEntity;

import java.math.BigDecimal;

public class BasketProductDto {
    private int id;
    private String name;
    private float price;
    private int quantity;
    private float totalPrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getTotalPrice(){return price * quantity;}

    public static BasketProductDto MapFrom(BasketEntity basketsEntity){
        ProductEntity product = basketsEntity.getProductByProductId();
        BasketProductDto productDto = new BasketProductDto();
        productDto.setId(basketsEntity.getId());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setQuantity(basketsEntity.getQuantity());
        return productDto;
    }
}
