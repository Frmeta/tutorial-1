package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository
{
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product){
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll(){
        return productData.iterator();
    }


    public Product findById(String id) {
        Iterator<Product> productIterator = findAll();

        while (productIterator.hasNext()){
            Product product = productIterator.next();
            if (product.getProductId().equals(id)){
                return product;
            }
        }
        throw new IllegalArgumentException("Product with id " + id + " not found");
    }


    // mengupdate produk
    public void save(Product updatedProduct) {
        String id = updatedProduct.getProductId();

        for (int i = 0; i < productData.size(); i++) {
            if (productData.get(i).getProductId().equals(id)) {
                productData.set(i, updatedProduct);
                return;
            }
        }
        throw new IllegalArgumentException("Product with id " + id + " not found");
    }


    public void deleteProductById(String id){
        Iterator<Product> productIterator = findAll();

        while (productIterator.hasNext()){
            Product product = productIterator.next();
            if (product.getProductId().equals(id)){
                productIterator.remove();
                return;
            }
        }

        throw new IllegalArgumentException("Product with id " + id + " not found");
    }
}
