package microservicesgft.teammicroserviciesgft.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import microservicesgft.teammicroserviciesgft.models.Product;
@Repository
public interface ProductRepository extends JpaRepository <Product, Integer> {

}
