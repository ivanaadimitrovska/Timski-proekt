package com.example.timski.repository;


import com.example.timski.model.ShoppingCart;
import com.example.timski.model.User;
import com.example.timski.model.enums.ShoppingCartEnumerations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    Optional<ShoppingCart> findByUserAndStatus(User user, ShoppingCartEnumerations shoppingCartEnum);
}
