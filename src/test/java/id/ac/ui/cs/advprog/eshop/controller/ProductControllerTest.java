package id.ac.ui.cs.advprog.eshop.controller;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.controller.ProductController;
import id.ac.ui.cs.advprog.eshop.service.CarServiceImpl;
import id.ac.ui.cs.advprog.eshop.service.ProductServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = ProductController.class)
public class ProductControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private ProductServiceImpl service;
    @MockBean
    private CarServiceImpl carService;
    @InjectMocks
    private ProductController controller;
    private List<Product> allProducts;

    private Product mockAddProduct(Product product){
        allProducts.add(product);
        return product;
    }

    private Product mockEditProduct(Product updatedProduct){
        for(Product product : allProducts){
            if(product.getProductId().equals(updatedProduct.getProductId())){
                product.setProductName(updatedProduct.getProductName());
                product.setProductQuantity(updatedProduct.getProductQuantity());
                return product;
            }
        }
        throw new IllegalArgumentException("Product with id " + updatedProduct.getProductId() + " not found");
    }

    Product productIpad(){
        Product product = new Product();
        product.setProductId("IPAD1");
        product.setProductName("Ipad");
        return product;
    }
    Product productLaptop(){
        Product product = new Product();
        product.setProductId("LAPTOP1");
        product.setProductName("Laptop");
        return product;
    }

    @BeforeEach
    void setUp(){
        allProducts = new ArrayList<>();
    }

    @AfterEach
    void deleteRepo(){
        allProducts = null;
    }

    @Test
    public void testHomePage() throws Exception{
        mvc.perform(get(""))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("ADV Shop")));
    }
    @Test
    public void testListPage() throws Exception{
        mvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Product's List")));
    }
    @Test
    public void testCreatePage() throws Exception{
        mvc.perform(get("/product/create"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Create New Product")));
    }
    @Test
    public void testCreateProductPost() throws Exception{

        // Create new product
        Product product = productIpad();
        when(service.create(product)).thenReturn(mockAddProduct(product));
        mvc.perform(post("/product/create").flashAttr("product",product))
                .andExpect(status().is3xxRedirection());

        // Check product list
        when(service.findAll()).thenReturn(allProducts);
        mvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Product's List")))
                .andExpect(content().string(containsString(product.getProductName())));
    }

    @Test
    public void testEditProductPage() throws Exception{

        // Create product
        Product product = productIpad();
        when(service.create(product)).thenReturn(mockAddProduct(product));
        mvc.perform(post("/product/create").flashAttr("product",product))
                .andExpect(status().is3xxRedirection());

        // Edit product
        when(service.findById(product.getProductId())).thenReturn(product);
        mvc.perform(get("/product/edit/" + product.getProductId()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Edit Product")));
    }

    @Test
    public void testEditProduct() throws Exception{

        // Create product
        Product product = productIpad();
        when(service.create(product)).thenReturn(mockAddProduct(product));
        mvc.perform(post("/product/create").flashAttr("product",product))
                .andExpect(status().is3xxRedirection());


        // Edit product
        Product updatedProduct = productIpad();
        updatedProduct.setProductName("Laptop");
        updatedProduct.setProductQuantity(12);

        when(service.save(updatedProduct)).thenReturn(mockEditProduct(updatedProduct));
        mvc.perform(post("/product/edit").flashAttr("product", updatedProduct))
                .andExpect(status().is3xxRedirection());

        // Check list product
        when(service.findAll()).thenReturn(allProducts);
        mvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Product's List")))
                .andExpect(content().string(containsString(updatedProduct.getProductName())));
    }
    @Test
    public void testEditUnknownProduct() throws Exception{

        Product product = productIpad();

        when(service.findById(product.getProductId())).thenThrow(new IllegalArgumentException());
        mvc.perform(post("/product/edit").flashAttr("product", product))
                .andExpect(status().is3xxRedirection());

    }

    @Test
    public void testDeleteProduct() throws Exception{

        // Create new product
        Product product = productIpad();
        when(service.create(product)).thenReturn(mockAddProduct(product));
        mvc.perform(post("/product/create").flashAttr("product",product))
                .andExpect(status().is3xxRedirection());

        // Check product list
        when(service.findAll()).thenReturn(allProducts);
        mvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Product's List")))
                .andExpect(content().string(containsString(product.getProductName())));

        // Delete product
        when(service.deleteProductById(product.getProductId())).thenReturn(allProducts.remove(product));
        mvc.perform(get("/product/delete/" + product.getProductId()))
                .andExpect(status().is3xxRedirection());

        assertEquals(0, allProducts.size());
    }
}