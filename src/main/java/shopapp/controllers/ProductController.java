package shopapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shopapp.dto.ProductDto;
import shopapp.models.ProductEntity;
import shopapp.models.UserEntity;
import shopapp.services.spring.SpringProductService;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    SpringProductService productService;

    @GetMapping(value = "/v2/products/{id}")
    public ProductDto findProductById(@PathVariable("id") int id, HttpServletResponse response) {
        ProductEntity productsEntity = productService.findProductById(id);

        if(productsEntity == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }

        ProductDto productDto = ProductDto.MapFrom(productsEntity);
        return productDto;
    }

    @GetMapping(value = "/v2/products")
    public List<ProductDto> findAllProducts() {
        List<ProductEntity> productsEntityList = productService.findAllProducts();
        List<ProductDto> productDtoList = new ArrayList<ProductDto>();

        for (ProductEntity product: productsEntityList) {
            productDtoList.add(ProductDto.MapFrom(product));
        }

        return productDtoList;
    }

    @PostMapping(value = "/v2/products")
    public ProductDto createProduct(@RequestBody ProductDto productDto, HttpServletResponse response) {
        ProductEntity product = productDto.MapTo();
        product = productService.createProduct(product);
        productDto = ProductDto.MapFrom(product);
        response.setStatus(HttpServletResponse.SC_CREATED);
        return productDto;
    }

    @PutMapping(value = "/v2/products")
    public ProductDto updateProduct(@RequestBody ProductDto productDto, HttpServletResponse response) {
        ProductEntity product = productDto.MapTo();
        product.setId(productDto.getId());

        if (productService.updateProduct(product)) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        productDto = ProductDto.MapFrom(product);
        return productDto;
    }

    @DeleteMapping(value = "/v2/products/{id}")
    public void deleteProductById(@PathVariable("id") int id, HttpServletResponse response) {
        if (productService.deleteProductById(id)) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
