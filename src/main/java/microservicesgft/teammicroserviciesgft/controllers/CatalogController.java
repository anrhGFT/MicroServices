package microservicesgft.teammicroserviciesgft.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import microservicesgft.teammicroserviciesgft.models.Product;
import microservicesgft.teammicroserviciesgft.services.CatalogService;

import java.util.List;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

    private CatalogService catalogService;

    public CatalogController (CatalogService catalogService){
        this.catalogService = catalogService;
    }

    @GetMapping("/products")
   public ResponseEntity<List<Product>> getAllCatalogs (){
        return new ResponseEntity<>(catalogService.findAllProducts(), HttpStatus.OK);
    }
    @GetMapping("/categories/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category) {
        List<Product> products = catalogService.findProductsByCategory(category);
        if (products.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(products);
    }
    @PostMapping("/createproduct")
    public ResponseEntity<Product> createData(@RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(catalogService.createProduct(product));
    }
    @PutMapping("/updateproduct/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product product) {
        product.setItemId(id);
        Product updatedProduct = catalogService.updateProduct(product);
        return ResponseEntity.ok(updatedProduct);
    }
    @DeleteMapping("/deleteproduct/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        try {
            catalogService.deleteProduct(id);
            return ResponseEntity.ok("Product with id " + id + " has been deleted");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with ID: " + id, e);
        }
    }
    
    @ExceptionHandler(RuntimeException.class)
public final ResponseEntity<Exception> handleAllExceptions(RuntimeException ex) {
    return new ResponseEntity<Exception>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
}

}
