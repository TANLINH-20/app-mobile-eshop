package com.example.nguyentanlinh_2122110398.adapter;

import static java.security.AccessController.getContext;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyentanlinh_2122110398.CartFragment;
import com.example.nguyentanlinh_2122110398.R;
import com.example.nguyentanlinh_2122110398.model.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

// CartAdapter.java
public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private List<Product> cartItems;

    public CartAdapter(List<Product> cartItems) {
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_cart_item, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Product product = cartItems.get(position);
        holder.productName.setText(product.getName());
        holder.productPrice.setText("$"+product.getPrice());
        holder.productRating.setText(product.getRating());
        holder.productQuantity.setText(product.getQuantity());

        // Sử dụng Picasso để tải ảnh từ URL
        Picasso.get().load(product.getImage()).into(holder.productImage);

        // Thêm sự kiện cho nút tăng giảm số lượng
        holder.increaseQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(product.getQuantity());
                quantity++;
                product.setQuantity(String.valueOf(quantity));
                notifyItemChanged(holder.getAdapterPosition());
                FragmentManager fragmentManager = ((FragmentActivity)holder.increaseQuantity.getContext()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_layout, new CartFragment()).commit();
            }
        });

        holder.decreaseQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(product.getQuantity());
                if (quantity > 0) {
                    quantity--;
                    product.setQuantity(String.valueOf(quantity));
                    notifyItemChanged(holder.getAdapterPosition());

                    if (quantity == 0) {
                        cartItems.remove(holder.getAdapterPosition());
                        notifyItemRemoved(holder.getAdapterPosition());
                    }
                    FragmentManager fragmentManager = ((FragmentActivity)holder.decreaseQuantity.getContext()).getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragment_layout, new CartFragment()).commit();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName;
        TextView productPrice;
        TextView productRating;
        TextView productQuantity;
        Button increaseQuantity;
        Button decreaseQuantity;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.food_image);
            productName = itemView.findViewById(R.id.food_name);
            productPrice = itemView.findViewById(R.id.food_price);
            productRating = itemView.findViewById(R.id.food_rating);
            productQuantity = itemView.findViewById(R.id.quantity);
            increaseQuantity = itemView.findViewById(R.id.increaseButton);
            decreaseQuantity = itemView.findViewById(R.id.decreaseButton);
        }
    }
}
