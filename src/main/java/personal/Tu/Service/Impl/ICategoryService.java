package personal.Tu.Service.Impl;

import org.springframework.data.domain.Sort;
import personal.Tu.Entity.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    List<Category> findAll();

    <S extends Category> S save(S entity);

    Optional<Category> findById(Integer integer);

    long count();

    void deleteById(Integer integer);

    void delete(Category entity);

    void deleteAll();

    List<Category> findAll(Sort sort);
}
