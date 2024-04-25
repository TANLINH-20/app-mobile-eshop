package com.example.nguyentanlinh_2122110398;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyentanlinh_2122110398.adapter.CartAdapter;
import com.example.nguyentanlinh_2122110398.adapter.CartManager;
import com.example.nguyentanlinh_2122110398.model.Product;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Product> cartItems = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        // Lấy danh sách sản phẩm từ CartManager
        List<Product> cartItems = CartManager.getInstance(requireContext()).getCartItems();
        recyclerView = view.findViewById(R.id.cart_rec);
        CartAdapter cartAdapter = new CartAdapter(cartItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(cartAdapter);


        Bundle bundle = getArguments();
        if (bundle != null) {
            String image = bundle.getString("image");
            String name = bundle.getString("name");
            String price = bundle.getString("price");
            String rating = bundle.getString("rating");
            String quantity = bundle.getString("quantity");

            Product product = new Product(image, name, price, rating, quantity);
            addToCart(product);
        }

        TextView totalTxt = view.findViewById(R.id.total);
        double totalPrice = 0;
        for (Product product : cartItems) {
            double price = Double.parseDouble(product.getPrice());
            int quantity = Integer.valueOf(product.getQuantity());
            totalPrice += (price * quantity);
        }
        totalTxt.setText("$" + String.format("%.2f", totalPrice));


        return view;
    }
    private void addToCart(Product product) {
        cartItems.add(product);
            recyclerView.getAdapter().notifyItemInserted(cartItems.size() - 1);
    }

}