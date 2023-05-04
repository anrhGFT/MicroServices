
package microservicesgft.teammicroservicesgft.services;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

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
      catalogService = new CatalogService(productRepository);
       
       
   }
   @Test
   public void updateProductTest(){
       
       Product existingProduct = new Product(1, "maceta","50L","category A", 10.00, 3);
       Product updatedProduct = new Product(1, "maceta","100L","category A",15.00, 5);
       when(productRepository.existsById(existingProduct.getItemId())).thenReturn(true);
       when(productRepository.save(updatedProduct)).thenReturn(updatedProduct);

       
       Product result = catalogService.updateProduct(updatedProduct);

       
       assertEquals(updatedProduct, result);
       verify(productRepository, times(1)).existsById(existingProduct.getItemId());
       verify(productRepository, times(1)).save(updatedProduct);
   }
  @Test
public void createProductTest() {
    Product product = new Product(3, "silla", "silla de madera", "jardinería",20.00, 5);
    when(productRepository.save(product)).thenReturn(product);

    Product result = catalogService.createProduct(product);

    assertEquals(product, result);
}

@Test
public void deleteProductTest() {
    int itemId = 1;
    Product deletedProduct = new Product(itemId, "maceta","50L","jardinería", 10.00, 3);
    when(productRepository.findById(itemId)).thenReturn(Optional.of(deletedProduct));

    catalogService.deleteProduct(itemId);

    verify(productRepository, times(1)).findById(itemId);
    verify(productRepository, times(1)).delete(deletedProduct);
}
@Test
public void deleteNonExistingProductTest() {
   int itemId = 1;
    when(productRepository.findById(itemId)).thenReturn(Optional.empty());

    assertThrows(ResponseStatusException.class, () -> catalogService.deleteProduct(itemId));

    verify(productRepository, times(1)).findById(itemId);
    verify(productRepository, times(0)).delete(null);
}
@Test
public void findProductsByCategoryTest() {
    String category = "category A";
    List<Product> productsInCategory = new ArrayList<>();
    productsInCategory.add(new Product(1, "maceta", "50L", category, 10.00, 3));
    productsInCategory.add(new Product(2, "silla", "silla de madera", category, 20.00, 5));
    when(productRepository.findProductsByCategory(category)).thenReturn(productsInCategory);

    List<Product> result = catalogService.findProductsByCategory(category);

    assertEquals(productsInCategory, result);
    verify(productRepository, times(1)).findProductsByCategory(category);
}

}