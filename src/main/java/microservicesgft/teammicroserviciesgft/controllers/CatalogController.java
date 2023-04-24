package microservicesgft.teammicroserviciesgft.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("products")
   public ResponseEntity<List<Product>> getAllCatalogs (){
        return new ResponseEntity<>(catalogService.findAllProducts(), HttpStatus.OK);
    }
    @PostMapping("createProduct")
    public ResponseEntity<Product> createData(@RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(catalogService.createProduct(product));
    }

    @ExceptionHandler(RuntimeException.class)
public final ResponseEntity<Exception> handleAllExceptions(RuntimeException ex) {
    return new ResponseEntity<Exception>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
}

}
