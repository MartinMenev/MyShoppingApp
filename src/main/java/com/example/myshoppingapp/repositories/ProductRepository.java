package com.example.myshoppingapp.repositories;

import com.example.myshoppingapp.models.products.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<List<Product>> findAllByUserId(Long id);

    Optional<Product> findByNameAndUserId (String productName, Long userid);

    @Modifying
    @Transactional
    @Query("update Product p set p.name = :newName where p.id = :id")
    void updateProduct( Long id, String newName);

    public void deleteById(long id);

    Optional<Product> getProductById(Long id);
}
