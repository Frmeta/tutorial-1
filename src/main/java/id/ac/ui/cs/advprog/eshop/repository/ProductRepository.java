package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository
{
    private long productIdCounter = 1;
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product){
        product.setProductId(String.valueOf(productIdCounter++));
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll(){
        return productData.iterator();
    }

    // mencari produk dengan id tertentu
    public Product findById(String id) {

        return productData.stream()
                .filter(product -> product.getProductId().equals(id))
                .findFirst()
                .get();
    }

    // delete produk berdasarkan id
    public void deleteById(String id) {
        productData.removeIf(product -> product.getProductId() == id);
    }

    // mengupdat produk
    public void save(Product updatedProduct) {
        String id = updatedProduct.getProductId();

        for (int i = 0; i < productData.size(); i++) {
            if (productData.get(i).getProductId().equals(id)) {
                productData.set(i, updatedProduct);
                return ;
            }
        }
        throw new IllegalArgumentException("Product with id " + id + " not found");
    }

}
