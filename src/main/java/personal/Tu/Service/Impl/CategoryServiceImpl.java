package personal.Tu.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import personal.Tu.Entity.Category;
import personal.Tu.Repository.CategoryRepository;
import personal.Tu.Service.ICategoryService;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public <S extends Category> S save(S entity) {
        return categoryRepository.save(entity);
    }

    @Override
    public Optional<Category> findById(Integer integer) {
        return categoryRepository.findById(integer);
    }

    @Override
    public long count() {
        return categoryRepository.count();
    }

    @Override
    public void deleteById(Integer integer) {
        categoryRepository.deleteById(integer);
    }

    @Override
    public void delete(Category entity) {
        categoryRepository.delete(entity);
    }

    @Override
    public void deleteAll() {
        categoryRepository.deleteAll();
    }

    @Override
    public List<Category> findAll(Sort sort) {
        return categoryRepository.findAll(sort);
    }

    @Override
    public Page<Category> findByCategoryNameContaining(String categoryName, Pageable pageable) {
        return categoryRepository.findByCategoryNameContaining(categoryName, pageable);
    }

    @Override
    public int countByCategoryNameContaining(String categoryName) {
        return categoryRepository.countByCategoryNameContaining(categoryName);
    }
}
