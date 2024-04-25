package com.example.nguyentanlinh_2122110398;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nguyentanlinh_2122110398.adapter.CartManager;
import com.example.nguyentanlinh_2122110398.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FoodDetailFragment extends Fragment {
    private ImageView backButton;
    private ImageView foodImage;
    private TextView foodName;
    private TextView foodPrice;
    private TextView foodRating;
    private TextView foodCategory;
    private TextView foodDescription;
    private Button decreaseButton;
    private Button increaseButton;
    private TextView quantityTextView;
    private Button addToCartButton;
    private int value = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_food_detail_fragment, container, false);

        backButton = view.findViewById(R.id.back_button);
        foodImage = view.findViewById(R.id.food_image);
        foodName = view.findViewById(R.id.food_name);
        foodPrice = view.findViewById(R.id.food_price);
        foodDescription = view.findViewById(R.id.food_description);
        foodRating = view.findViewById(R.id.food_rating);
        foodCategory = view.findViewById(R.id.food_category);
        decreaseButton = view.findViewById(R.id.decreaseButton);
        increaseButton = view.findViewById(R.id.increaseButton);
        quantityTextView = view.findViewById(R.id.textView);
        addToCartButton = view.findViewById(R.id.add_cart);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().popBackStack();
            }
        });

        Bundle bundle = getArguments();
        if (bundle != null) {
            String image = bundle.getString("image");
            String name = bundle.getString("name");
            String price = bundle.getString("price");
            String rating = bundle.getString("rating");
            String description = bundle.getString("description");
            String category = bundle.getString("category");

            Picasso.get().load(image).into(foodImage);
            foodName.setText("Product's name: "+name);
            foodPrice.setText("Price: "+price);
            foodRating.setText("Evaluate: "+rating);
            foodDescription.setText("Description: "+description);
            foodCategory.setText("Category: "+category);
        }

        increaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value++;
                quantityTextView.setText(String.valueOf(value));
            }
        });

        decreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (value > 1) {
                    value--;
                    quantityTextView.setText(String.valueOf(value));
                }
            }
        });

        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendProductToCart();
                Toast.makeText(getActivity(), "Add to cart success", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void sendProductToCart() {
        String image = getArguments().getString("image");
        String name = getArguments().getString("name");
        String price = getArguments().getString("price");
        String rating = getArguments().getString("rating");
        String quantity = quantityTextView.getText().toString();

        // Tạo bundle để chứa thông tin sản phẩm
        Bundle bundle = new Bundle();
        bundle.putString("image", image);
        bundle.putString("name", name);
        bundle.putString("price", price);
        bundle.putString("rating", rating);
        bundle.putString("quantity", quantity);

        // Tạo CartFragment và gửi thông tin sản phẩm
        CartFragment cartFragment = new CartFragment();
        cartFragment.setArguments(bundle);
        Product product = new Product(image, name, price, rating, quantity);
        CartManager.getInstance(requireContext()).addToCart(product);
        Toast.makeText(getActivity(), "Add to cart success", Toast.LENGTH_SHORT).show();
    }

}

