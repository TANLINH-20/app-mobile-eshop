package com.example.nguyentanlinh_2122110398.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyentanlinh_2122110398.FoodDetailFragment;
import com.example.nguyentanlinh_2122110398.R;
import com.example.nguyentanlinh_2122110398.model.VerItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ItemVerAdapter extends RecyclerView.Adapter<ItemVerAdapter.ItemViewHolder> {

    private Context context;
    private ArrayList<VerItem> list;

    public ItemVerAdapter(ArrayList<VerItem> list, Context context) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ver_layout, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

        VerItem currentVerItem = list.get(position);

        Picasso.get().load(currentVerItem.getImage()).into(holder.imageView); //Lấy ảnh từ URL
        holder.name.setText(currentVerItem.getTitle());
        holder.price.setText("$"+String.valueOf(currentVerItem.getPrice()));
        holder.rating.setText(String.valueOf(currentVerItem.getRating().getRate()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FoodDetailFragment foodDetailFragment = new FoodDetailFragment();
                Bundle bundle = new Bundle();
                bundle.putString("image", currentVerItem.getImage());
                bundle.putString("name", currentVerItem.getTitle());
                bundle.putString("price", String.valueOf(currentVerItem.getPrice()));
                bundle.putString("rating", String.valueOf(currentVerItem.getRating().getRate()));
                bundle.putString("description", currentVerItem.getDescription());
                bundle.putString("category", currentVerItem.getCategory());

                foodDetailFragment.setArguments(bundle);

                // Lấy FragmentManager từ Context của holder.itemView
                FragmentManager fragmentManager = ((FragmentActivity)holder.itemView.getContext()).getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_layout, foodDetailFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class  ItemViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name, rating, price;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ver_img);
            name = itemView.findViewById(R.id.name);
            rating = itemView.findViewById(R.id.rating);
            price = itemView.findViewById(R.id.price);
        }
    }
}
