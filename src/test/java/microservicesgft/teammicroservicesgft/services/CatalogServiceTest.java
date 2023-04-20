
package microservicesgft.teammicroservicesgft.services;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import microservicesgft.teammicroserviciesgft.models.Product;
import microservicesgft.teammicroserviciesgft.repositories.ProductRepository;
import microservicesgft.teammicroserviciesgft.services.CatalogService;

@ExtendWith(MockitoExtension.class)
public class CatalogServiceTest {

    @InjectMocks
    private CatalogService catalogService;
   
    @Mock
    private ProductRepository productRepository;
   
    private List<Product> catalog = new ArrayList<>();
   
    @BeforeEach
    void setUp(){
        catalog.add(new Product(1, "maceta","50L", 10.00, 3));
        catalog.add(new Product(2, "bicicleta","bici electrica", 300.00, 10));
        when(productRepository.findAll()).thenReturn(catalog);
   }

  @Test
  public void findAllProduct(){
    List<Product>result=catalogService.findAllProducts();
    Object[]catalogArr=catalog.toArray();
    Object[] resultArr=result.toArray();
    assertArrayEquals(catalogArr,resultArr);
  }
}