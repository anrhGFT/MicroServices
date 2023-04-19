package microservicesgft.teammicroserviciesgft.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import microservicesgft.teammicroserviciesgft.models.Product;
import microservicesgft.teammicroserviciesgft.services.CatalogService;

import java.util.List;

@RestController
public class CatalogController {

    private CatalogService catalogService;

    public CatalogController (CatalogService catalogService){
        this.catalogService = catalogService;
    }

    @GetMapping("api/catalog")
    ResponseEntity<List<Product>> getAllCatalogs (){
        return new ResponseEntity<>(catalogService.findAllProducts(), HttpStatus.OK);
    }

}
