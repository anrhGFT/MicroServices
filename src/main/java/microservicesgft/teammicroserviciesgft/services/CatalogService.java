package microservicesgft.teammicroserviciesgft.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import microservicesgft.teammicroserviciesgft.models.Product;
import microservicesgft.teammicroserviciesgft.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CatalogService {
 
   private ProductRepository productRepository;


    public CatalogService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public Product getProductById(int item_id) {
        Optional<Product> optionalProduct = productRepository.findById(item_id);
        if (optionalProduct.isPresent()) {
            return optionalProduct.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with ID: " + item_id);
        }
    }

    public List<Product> findAllProducts(){
        return  productRepository.findAll();
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Product product) {
        if (productRepository.existsById(product.getItemId())) {
    		return productRepository.save(product);
    	} else {
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product does not exist"); 
    	}
    }


    public void deleteProduct(int item_id) {
        Optional<Product> optionalProduct = productRepository.findById(item_id);
        if (optionalProduct.isPresent()) {
            productRepository.delete(optionalProduct.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product does not exist"); 
        }
    }

}