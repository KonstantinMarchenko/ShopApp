package shopapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import shopapp.models.ProductEntity;
import shopapp.services.ProductService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping(value = "/v1/products/{id}")
    public ProductEntity findProductById(@PathVariable("id") int id) {
        return productService.findProductById(id);
    }

    @GetMapping(value = "/v1/products")
    public List<ProductEntity> findAllProducts() {
        return productService.findAllProducts();
    }

    @PostMapping(value = "/v1/products")
    public void createProduct(@RequestBody ProductEntity productEntity, HttpServletResponse response) {
        productService.createProduct(productEntity);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    @PutMapping(value = "/v1/products")
    public void updateProduct(@RequestBody ProductEntity productEntity, HttpServletResponse response) {
        if (productService.updateProduct(productEntity)) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/v1/products/{id}")
    public void deleteProductById(@PathVariable("id") int id, HttpServletResponse response) {
        if (productService.deleteProductById(id)) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
