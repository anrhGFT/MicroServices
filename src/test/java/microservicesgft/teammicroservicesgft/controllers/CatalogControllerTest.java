 package microservicesgft.teammicroservicesgft.controllers;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import microservicesgft.teammicroserviciesgft.controllers.CatalogController;
import microservicesgft.teammicroserviciesgft.models.Product;
import microservicesgft.teammicroserviciesgft.services.CatalogService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CatalogControllerTest {
    
   
    @Mock
    private CatalogService catalogService;
    @InjectMocks
    private CatalogController catalogController;
    private List<Product> productList;
    private MockMvc mockMvc;
    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(catalogController).build();
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
    public void testGetAllProducts() throws Exception {
       
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1, "Product A", "Description A", 10.0, 20));
        productList.add(new Product(2, "Product B", "Description B", 20.0, 30));

       
        when(catalogService.findAllProducts()).thenReturn(productList);

        mockMvc.perform(get("/catalog/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].itemId", is(1)))
                .andExpect(jsonPath("$[0].name", is("Product A")))
                .andExpect(jsonPath("$[0].description", is("Description A")))
                .andExpect(jsonPath("$[0].price", is(10.0)))
                .andExpect(jsonPath("$[0].stock", is(20)))
                .andExpect(jsonPath("$[1].itemId", is(2)))
                .andExpect(jsonPath("$[1].name", is("Product B")))
                .andExpect(jsonPath("$[1].description", is("Description B")))
                .andExpect(jsonPath("$[1].price", is(20.0)))
                .andExpect(jsonPath("$[1].stock", is(30)));

       
        verify(catalogService, times(1)).findAllProducts();
    }
    @Test
    void testCreateProduct() throws Exception {
        Product product = new Product();
        product.setName("Test Product");
        product.setDescription("Test Description");
        product.setPrice(10.0);
        product.setStock(100);
    
        when(catalogService.createProduct(any(Product.class))).thenReturn(product);
    
        mockMvc.perform(post("/catalog/createproduct")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(product)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Test Product")))
                .andExpect(jsonPath("$.description", is("Test Description")))
                .andExpect(jsonPath("$.price", is(10.0)))
                .andExpect(jsonPath("$.stock", is(100)));
    
            }
    @Test
    public void createProductTest() {
      Product product = new Product();
      product.setName("Product 1");
      product.setDescription("Product 1 description");
      product.setPrice(100.0);
      product.setStock(10);
      when(catalogService.createProduct(any(Product.class))).thenReturn(product);
  
      ResponseEntity<Product> responseEntity = catalogController.createData(product);
  
      assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
      assertEquals(product, responseEntity.getBody());
    } 

  




    @Test
    void testUpdateProduct() {
        // Arrange
        int productId = 1;
        Product productToUpdate = new Product();
        productToUpdate.setItemId(productId);
        productToUpdate.setName("Updated Product Name");
        productToUpdate.setDescription("Updated Product Description");
        productToUpdate.setPrice(10.0);
        productToUpdate.setStock(10);
        Product updatedProduct = new Product();
        updatedProduct.setItemId(productId);
        updatedProduct.setName("Updated Product Name");
        updatedProduct.setDescription("Updated Product Description");
        updatedProduct.setPrice(10.0);
        updatedProduct.setStock(10);
        when(catalogService.updateProduct(any(Product.class))).thenReturn(updatedProduct);
        
        // Act
        ResponseEntity<Product> response = catalogController.updateProduct(productId, productToUpdate);
        
        // Assert
        verify(catalogService).updateProduct(productToUpdate);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedProduct, response.getBody());
    }
   
}