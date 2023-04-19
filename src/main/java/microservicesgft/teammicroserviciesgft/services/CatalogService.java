package microservicesgft.teammicroserviciesgft.services;

import org.springframework.stereotype.Service;

import microservicesgft.teammicroserviciesgft.models.Product;
import microservicesgft.teammicroserviciesgft.repositories.ProductRepository;

import java.util.List;

@Service
public class CatalogService {
 
   private ProductRepository productRepository;


    public CatalogService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAllProducts(){
        return  productRepository.findAll();
    }


}