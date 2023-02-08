package personal.Tu.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import personal.Tu.Entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Page<Product> findByProductNameContaining(String productName, Pageable pageable);
    int countByProductNameContaining(String productName);
}
