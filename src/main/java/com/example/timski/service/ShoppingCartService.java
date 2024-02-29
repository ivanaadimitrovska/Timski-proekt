package com.example.timski.service;


import com.example.timski.model.Product;
import com.example.timski.model.ShoppingCart;
import com.example.timski.model.User;
import com.example.timski.model.enums.ShoppingCartEnumerations;
import com.example.timski.model.errors.InvalidUser;
import com.example.timski.model.errors.ProductAlreadyInShoppingCart;
import com.example.timski.model.errors.ProductNotFound;
import com.example.timski.model.errors.ShoppingCartNotFound;
import com.example.timski.repository.ProductRepository;
import com.example.timski.repository.ShoppingCartRepository;
import com.example.timski.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartsRepository;
    private final UserRepository userRepository;
    private final ProductRepository productService;

    public ShoppingCartService(ShoppingCartRepository shoppingCartsRepository, UserRepository userRepository, ProductRepository productService) {
        this.shoppingCartsRepository = shoppingCartsRepository;
        this.userRepository = userRepository;
        this.productService = productService;
    }


    public List<Product> listAllProductsInShoppingCart(Long cartId) {
        if (this.shoppingCartsRepository.findById(cartId).isEmpty()) {
            throw new ShoppingCartNotFound(cartId);
        }
        return shoppingCartsRepository.findById(cartId).get().getProductList();
    }


    public ShoppingCart getActiveShoppingCart(String username) {

        User user = this.userRepository.findByUsername(username)
                .orElseThrow(InvalidUser::new);

        return this.shoppingCartsRepository
                .findByUserAndStatus(user, ShoppingCartEnumerations.CREATED)
                .orElseGet(() -> {
                    ShoppingCart cart = new ShoppingCart(user);
                    return this.shoppingCartsRepository.save(cart);
                });


    }


    public ShoppingCart addProductToShoppingCart(String username, Long productId) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(username);
        Product product = productService.findById(productId).orElseThrow(() -> new ProductNotFound(productId));
        if (shoppingCart.getProductList().stream().filter(r -> r.getId().equals(productId)).toList().size() > 0) {
            throw new ProductAlreadyInShoppingCart(productId, username);
        }
        shoppingCart.getProductList().add(product);
        return shoppingCartsRepository.save(shoppingCart);
    }
}
