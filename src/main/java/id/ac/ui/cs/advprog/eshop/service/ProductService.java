package id.ac.ui.cs.advprog.eshop.service;


import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;

public interface ProductService {
    public Product create(Product product);
    public List<Product> findAll();
    //public void editProduct(String id, String name, int quantity);
    public void save(Product product);
    public Product findById(String id);
}
