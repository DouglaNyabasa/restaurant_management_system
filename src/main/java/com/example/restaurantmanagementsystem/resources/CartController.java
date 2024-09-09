package com.example.restaurantmanagementsystem.resources;

import com.example.restaurantmanagementsystem.model.Cart;
import com.example.restaurantmanagementsystem.model.CartItem;
import com.example.restaurantmanagementsystem.model.User;
import com.example.restaurantmanagementsystem.request.AddCartItemRequest;
import com.example.restaurantmanagementsystem.request.UpdateCartItemRequest;
import com.example.restaurantmanagementsystem.service.CartService;
import com.example.restaurantmanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CartController {

    @Autowired
    private UserService userService;


    @Autowired
    private CartService cartService;

    @PutMapping("/cart/add")
    public ResponseEntity<CartItem> addItemToCart(@RequestBody AddCartItemRequest request, @RequestHeader("Authorization")String jwt) throws Exception {
        CartItem cartItem = cartService.addItemToCart(request, jwt);

        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    @PutMapping("/cart-item/update")
    public ResponseEntity<CartItem> updateCartItemQuantity(@RequestBody UpdateCartItemRequest request, @RequestHeader("Authorization")String jwt) throws Exception {
        CartItem cartItem = cartService.updateCartItemQuantity(request.getCartItemId(), request.getQuantity());


        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    @DeleteMapping("/cart-item/{id}/remove")
    public ResponseEntity<Cart> removeItemFromCart(@PathVariable Long id, @RequestBody AddCartItemRequest request, @RequestHeader("Authorization")String jwt) throws Exception {
        Cart cartItem = cartService.removeItemFromCart(id,jwt);

        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }


    @DeleteMapping("/cart/clear")
    public ResponseEntity<Cart> clearCart(@RequestHeader("Authorization")String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Cart cartItem = cartService.clearCart(user.getId());


        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    @DeleteMapping("/cart")
    public ResponseEntity<Cart> findUserCart(@RequestHeader("Authorization")String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Cart cartItem = cartService.findCartByUserId(user.getId());

        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

}
