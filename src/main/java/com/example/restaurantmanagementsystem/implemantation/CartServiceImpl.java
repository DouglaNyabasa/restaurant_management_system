package com.example.restaurantmanagementsystem.implemantation;

import com.example.restaurantmanagementsystem.model.Cart;
import com.example.restaurantmanagementsystem.model.CartItem;
import com.example.restaurantmanagementsystem.model.Food;
import com.example.restaurantmanagementsystem.model.User;
import com.example.restaurantmanagementsystem.repository.CartItemRepository;
import com.example.restaurantmanagementsystem.repository.CartRepository;
import com.example.restaurantmanagementsystem.repository.FoodRepository;
import com.example.restaurantmanagementsystem.request.AddCartItemRequest;
import com.example.restaurantmanagementsystem.service.CartService;
import com.example.restaurantmanagementsystem.service.FoodService;
import com.example.restaurantmanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private FoodService foodService;
    @Override
    public CartItem addItemToCart(AddCartItemRequest request, String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        Food food = foodService.findFoodById(request.getFoodId());
        Cart cart = cartRepository.findByCustomerId(user.getId());
        for (CartItem cartItem : cart.getItem()){
            if (cartItem.getFood().equals(food)){
                int newQuantity = cartItem.getQuantity()+ request.getQuantity();
                return updateCartItemQuantity(cartItem.getId(),newQuantity);
            }
        }
        CartItem newCartItem = new CartItem();
        newCartItem.setFood(food);
        newCartItem.setCart(cart);
        newCartItem.setQuantity(request.getQuantity());
        newCartItem.setIngredients(request.getIngredients());
        newCartItem.setTotalPrice(request.getQuantity() * food.getPrice());

        CartItem saveCartItem = cartItemRepository.save(newCartItem);
        cart.getItem().add(saveCartItem);
        return saveCartItem;
    }

    @Override
    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception {

        Optional<CartItem> optionalCartItem = cartItemRepository.findById(cartItemId);
        if (optionalCartItem.isEmpty()){
            throw new Exception("Cart Item Not found");
        }
        CartItem item = optionalCartItem.get();
        item.setQuantity(quantity);
        item.setTotalPrice(item.getFood().getPrice() * quantity);
        return cartItemRepository.save(item);
    }

    @Override
    public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        Cart cart = cartRepository.findByCustomerId(user.getId());
        Optional<CartItem> optionalCartItem = cartItemRepository.findById(cartItemId);
        if (optionalCartItem.isEmpty()){
            throw new Exception("Cart Item Not found");
        }
        CartItem item = optionalCartItem.get();
        cart.getItem().remove(item);
        return cartRepository.save(cart);
    }

    @Override
    public Long calculateCartTotals(Cart cart) throws Exception {

        Long total = 0L;
        for (CartItem cartItem: cart.getItem()){
            total = cartItem.getFood().getPrice() * cartItem.getQuantity();
        }
        return total;
    }

    @Override
    public Cart findCartById(Long id) throws Exception {
        Optional<Cart> optionalCart = cartRepository.findById(id);
        if (optionalCart.isEmpty()){
            throw new Exception("Cart not found with id " + id);
        }
        return optionalCart.get();
    }

    @Override
    public Cart findCartByUserId(Long userId) throws Exception {

        Cart cart = cartRepository.findByCustomerId(userId);
        cart.setTotal(calculateCartTotals(cart));
        return cart;
    }

    @Override
    public Cart clearCart(Long userId)throws Exception {
        Cart cart = findCartByUserId((userId));
        cart.getItem().clear();
        return cartRepository.save(cart);
    }
}
