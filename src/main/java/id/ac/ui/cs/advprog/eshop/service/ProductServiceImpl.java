package id.ac.ui.cs.advprog.eshop.service;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ProductServiceImpl implements  ProductService{
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product create(Product product){
        productRepository.create(product);
        return product;
    }

    @Override
    public List<Product> findAll(){
        Iterator<Product> productIterator = productRepository.findAll();
        List<Product> allProduct = new ArrayList<>();
        productIterator.forEachRemaining((allProduct::add));
        return allProduct;
    }

//    @Override
//    public void editProduct(String id, String name, int quantity){
//        Product p = productRepository.findById(id).get();
//        p.setProductName(name);
//        p.setProductQuantity(quantity);
//
//        productRepository.save(p);
//    }
    @Override
    public void save(Product product){
        productRepository.save(product);
    }

    @Override
    public Product findById(String id) {
        return productRepository.findById(id);
    }
}
