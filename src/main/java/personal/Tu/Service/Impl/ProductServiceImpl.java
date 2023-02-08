package personal.Tu.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import personal.Tu.Entity.Product;
import personal.Tu.Repository.ProductRepository;
import personal.Tu.Service.IProductService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public Page<Product> findByProductNameContaining(String productName, Pageable pageable) {
        return productRepository.findByProductNameContaining(productName, pageable);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public <S extends Product> S save(S entity) {
        return productRepository.save(entity);
    }

    @Override
    public Optional<Product> findById(Integer integer) {
        return productRepository.findById(integer);
    }

    @Override
    public long count() {
        return productRepository.count();
    }

    @Override
    public void deleteById(Integer integer) {
        productRepository.deleteById(integer);
    }

    @Override
    public void delete(Product entity) {
        productRepository.delete(entity);
    }

    @Override
    public void deleteAll() {
        productRepository.deleteAll();
    }

    @Override
    public List<Product> findAll(Sort sort) {
        return productRepository.findAll(sort);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public int countByProductNameContaining(String productName) {
        return productRepository.countByProductNameContaining(productName);
    }
}
