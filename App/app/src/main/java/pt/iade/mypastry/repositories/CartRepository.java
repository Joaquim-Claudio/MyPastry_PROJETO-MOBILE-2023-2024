package pt.iade.mypastry.repositories;

import java.util.ArrayList;

import pt.iade.mypastry.models.Cart;
import pt.iade.mypastry.models.User;

public class CartRepository {

    private static ArrayList<Cart> carts = new ArrayList<Cart>();

    public static void populate() {
        carts.add(new Cart(1));
    }

    public static ArrayList<Cart> getCarts(){
        return carts;
    }

    public static Cart getCart(int id){
        for (Cart u : carts){
            if (u.getId() == id){
                return u;
            }
        }
        return null;
    }
    public static void addCart(Cart cart) {
        carts.add(cart);
    }

    public static boolean removeCart(int id) {
        return carts.removeIf(u -> u.getId() == id);
    }

}
