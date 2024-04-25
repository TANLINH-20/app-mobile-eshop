package com.example.nguyentanlinh_2122110398.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.nguyentanlinh_2122110398.model.Product;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static final String CART_PREFERENCES = "CART_PREFERENCES";
    private static final String CART_ITEMS_KEY = "CART_ITEMS_KEY";
    private static CartManager instance;
    private List<Product> cartItems;
    private SharedPreferences sharedPreferences;
    private Gson gson;

    private CartManager(Context context) {
        cartItems = new ArrayList<>();
        sharedPreferences = context.getSharedPreferences(CART_PREFERENCES, Context.MODE_PRIVATE);
        gson = new Gson();
        loadCartItems();
    }

    public static CartManager getInstance(Context context) {
        if (instance == null) {
            instance = new CartManager(context);
        }
        return instance;
    }

    public List<Product> getCartItems() {
        return cartItems;
    }

    public void addToCart(Product product) {
        cartItems.add(product);
        saveCartItems();
    }

    private void loadCartItems() {
        String json = sharedPreferences.getString(CART_ITEMS_KEY, null);
        if (json != null) {
            Type type = new TypeToken<List<Product>>() {}.getType();
            cartItems = gson.fromJson(json, type);
        }
    }

    private void saveCartItems() {
        String json = gson.toJson(cartItems);
        sharedPreferences.edit().putString(CART_ITEMS_KEY, json).apply();
    }
}

