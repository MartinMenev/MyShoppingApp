package com.example.myshoppingapp.repository;

import com.example.myshoppingapp.model.products.Product;
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


    @Modifying
    @Transactional
    @Query("update Product p set p.name = :newName where p.id = :id")
    void updateProductName(Long id, String newName);

    public void deleteById(long id);

    Optional<Product> getProductById(Long id);

    @Query("select max(p.id) from Product p")
    Optional <Long> findLatestId();

    @Modifying
    @Transactional
    @Query("update Product p set p.position = case " +
            "when (p.position = :pos1) then :pos2 " +
            "when (p.position = :pos2) then :pos1 " +
            "end " +
    "WHERE p.position in (:pos1, :pos2)")
    void swapProductOrder(Long pos1, Long pos2);

    Product findFirstByPositionGreaterThanAndUserIdOrderByPositionAsc(Long position, Long userId);

    Product findFirstByPositionLessThanAndUserIdOrderByPositionDesc(Long position, Long userId);


    Optional<List<Product>> findAllByBuyerId(Long userId);

}
