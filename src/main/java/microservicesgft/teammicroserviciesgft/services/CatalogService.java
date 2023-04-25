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




}