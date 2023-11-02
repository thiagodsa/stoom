package br.com.stoom.store.repository;

import br.com.stoom.store.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByBrandId(Long brandId);

    @Query(value = "select p from Product p join p.categories c where c.id = :categoryId")
    List<Product> findAllByCategory(Long categoryId);
}