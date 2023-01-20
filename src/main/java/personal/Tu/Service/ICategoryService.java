package personal.Tu.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import personal.Tu.Entity.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    Page<Category> findAll(Pageable pageable);

    List<Category> findAll();

    <S extends Category> S save(S entity);

    Optional<Category> findById(Integer integer);

    long count();

    void deleteById(Integer integer);

    void delete(Category entity);

    void deleteAll();

    List<Category> findAll(Sort sort);

    Page<Category> findByCategoryNameContaining(String categoryName, Pageable pageable);
}
