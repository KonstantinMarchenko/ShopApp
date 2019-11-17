package shopapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shopapp.models.ProductsEntity;
import shopapp.services.SpringProductService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    SpringProductService productService;

    @GetMapping(value = "/v1/products/{id}")
    public ProductsEntity findProductById(@PathVariable("id") int id) {
        return productService.findProductById(id);
    }

    @GetMapping(value = "/v1/products")
    public List<ProductsEntity> findAllProducts() {
        return productService.findAllProducts();
    }

    @PostMapping(value = "/v1/products")
    public void createProduct(@RequestBody ProductsEntity productEntity, HttpServletResponse response) {
        productService.createProduct(productEntity);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    @PutMapping(value = "/v1/products")
    public void updateProduct(@RequestBody ProductsEntity productEntity, HttpServletResponse response) {
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
