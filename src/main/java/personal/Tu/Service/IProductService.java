package personal.Tu.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import personal.Tu.Entity.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    Page<Product> findByProductNameContaining(String productName, Pageable pageable);

    List<Product> findAll();

    <S extends Product> S save(S entity);

    Optional<Product> findById(Integer integer);

    long count();

    void deleteById(Integer integer);

    void delete(Product entity);

    void deleteAll();

    List<Product> findAll(Sort sort);

    Page<Product> findAll(Pageable pageable);

    int countByProductNameContaining(String productName);
}
