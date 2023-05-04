package microservicesgft.teammicroserviciesgft.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import microservicesgft.teammicroserviciesgft.models.Product;
@Repository
public interface ProductRepository extends JpaRepository <Product, Integer> {

    @Query("SELECT p FROM Product p WHERE p.category = ?1")
    List<Product> findProductsByCategory(String category);
}
