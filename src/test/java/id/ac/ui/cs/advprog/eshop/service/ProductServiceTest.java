package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    ProductServiceImpl service;

    @MockBean
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testEdit(){

        // Make new product
        Product product = new Product();
        product.setProductId("abcde");
        product.setProductName("Nanas");
        product.setProductQuantity(10);
        service.create(product);


        // Change the name & quantity of product
        product = service.findById("abcde");
        product.setProductName("Nanas busuk");
        product.setProductQuantity(8);
        service.save(product);

        // Verify
        product = service.findById("abcde");
        assertEquals(product.getProductId(), "abcde");
        assertEquals(product.getProductName(), "Nanas busuk");
        assertEquals(product.getProductQuantity(), 8);
    }

    @Test
    void testDelete(){
        // Make new product
        Product product = new Product();
        product.setProductId("abcde");
        product.setProductName("Nanas");
        product.setProductQuantity(10);
        service.create(product);

        // Delete product
        service.deleteProductById("abcde");

        // Verify
        List<Product> productList = service.findAll();
        for (Product p: productList) {
            assertNotEquals(p.getProductId(), "abcde");
        }

    }

}