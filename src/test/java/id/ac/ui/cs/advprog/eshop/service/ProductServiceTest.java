package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    //@InjectMocks
    //ProductRepository productRepository;
    @InjectMocks
    ProductServiceImpl service;


//    @BeforeEach
//    public void init() {
//        MockitoAnnotations.openMocks(this);
//    }

    @Test
    void testCreateProduct() {
        for (int i = 0; i < 5;i++){
            Product product = new Product();
            product.setProductName(String.valueOf(i+10));
            product.setProductQuantity(i+20);


            service.create(product);
        }

        List<Product> productList = service.findAll();

        for (int i = 0; i < 5;i++){
            Product product = productList.get(i);

            assertEquals(product.getProductId(), String.valueOf(i+1));
            assertEquals(product.getProductName(), String.valueOf(i+10));
            assertEquals(product.getProductQuantity(), i+20);
        }
    }
//
//    @Test
//    void testSaveProduct(){
//        for (int i = 0; i < 5;i++){
//            Product product = new Product();
//            product.setProductId("XXX");
//            product.setProductName("XXX");
//            product.setProductQuantity(1);
//        }
//    }

//    @Test
//    void testCreateOrSaveEmployee() {
//        Employee employee = new Employee("Lokesh", "Gupta");
//
//        service.save(employee);
//
//        verify(dao, times(1)).save(employee);
//    }


//    @Test
//    void testFindAllEmployees() {
//        List<Employee> list = new ArrayList<Employee>();
//        Employee empOne = new Employee("John", "John");
//        Employee empTwo = new Employee("Alex", "kolenchiski");
//        Employee empThree = new Employee("Steve", "Waugh");
//
//        list.add(empOne);
//        list.add(empTwo);
//        list.add(empThree);
//
//        when(repo.findAll()).thenReturn(list);
//
//        //test
//        List<Employee> empList = service.findAll();
//
//        assertEquals(3, empList.size());
//        verify(dao, times(1)).findAll();
//    }



}
