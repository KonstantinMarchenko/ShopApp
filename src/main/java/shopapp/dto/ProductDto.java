package shopapp.dto;

import shopapp.models.ProductEntity;

public class ProductDto {
    private int id;
    private String name;
    private float price;
    private int quantity;

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

    public static ProductDto MapFrom(ProductEntity productsEntity){
        ProductDto productDto = new ProductDto();
        productDto.setId(productsEntity.getId());
        productDto.setName(productsEntity.getName());
        productDto.setPrice(productsEntity.getPrice());
        productDto.setQuantity(productsEntity.getQuantity());
        return productDto;
    }

    public ProductEntity MapTo(){
        ProductEntity productsEntity = new ProductEntity();
        productsEntity.setName(this.getName());
        productsEntity.setPrice(this.getPrice());
        productsEntity.setQuantity(this.getQuantity());
        return productsEntity;
    }
}
