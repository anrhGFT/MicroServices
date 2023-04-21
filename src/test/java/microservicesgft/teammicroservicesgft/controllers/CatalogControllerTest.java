 package microservicesgft.teammicroservicesgft.controllers;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyList;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import microservicesgft.teammicroserviciesgft.controllers.CatalogController;
import microservicesgft.teammicroserviciesgft.models.Product;
import microservicesgft.teammicroserviciesgft.services.CatalogService;

@ExtendWith(MockitoExtension.class)
public class CatalogControllerTest {
    
    @Mock
    private CatalogService catalogService;
    
    @InjectMocks
    private CatalogController catalogController;
    
    private List<Product> productList;
    
    @BeforeEach
    public void setup() {
        productList = new ArrayList<>();
        Product product1 = new Product();
        product1.setItemId(1);
        product1.setName("Product 1");
        product1.setDescription("Product 1 Description");
        product1.setPrice(10.99);
        product1.setStock(20);
        productList.add(product1);
        Product product2 = new Product();
        product2.setItemId(2);
        product2.setName("Product 2");
        product2.setDescription("Product 2 Description");
        product2.setPrice(15.99);
        product2.setStock(10);
        productList.add(product2);
    }
    
    @Test
    public void getAllCatalogsTest() {
        when(catalogService.findAllProducts()).thenReturn(productList);
        ResponseEntity<List<Product>> response = catalogController.getAllCatalogs();
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(2, response.getBody().size());
        Assertions.assertEquals(productList, response.getBody());
    }
    
}