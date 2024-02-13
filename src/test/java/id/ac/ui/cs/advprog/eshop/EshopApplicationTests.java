package id.ac.ui.cs.advprog.eshop;

import id.ac.ui.cs.advprog.eshop.controller.ProductController;
import id.ac.ui.cs.advprog.eshop.service.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EshopApplicationTests {
    @Autowired
    private ProductController myController;

    @Autowired
    private ProductServiceImpl myService;

    @Test
    void contextLoads() {
        assertNotNull(myController);
        assertNotNull(myService);
        EshopApplication.main(new String[] {});
    }

}
