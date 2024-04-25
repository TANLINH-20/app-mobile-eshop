package com.example.nguyentanlinh_2122110398;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.nguyentanlinh_2122110398.adapter.ItemVerAdapter;
import com.example.nguyentanlinh_2122110398.api.ApiService;
import com.example.nguyentanlinh_2122110398.model.VerItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {

    private RecyclerView recyclerView_ver;
    // Vertical
    private ArrayList<VerItem> verItemList;
    private ItemVerAdapter adapter_ver;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView_ver = view.findViewById(R.id.recycler_view2);

        // Vertical RecycleView
        verItemList = new ArrayList<>();
        adapter_ver = new ItemVerAdapter(verItemList, getActivity());
        recyclerView_ver.setAdapter(adapter_ver);
        recyclerView_ver.setHasFixedSize(true);
        recyclerView_ver.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView_ver.setNestedScrollingEnabled(false);
        loadProducts();
        return view;
    }

    private void loadProducts() {
        ApiService.apiService.getProduct().enqueue(new Callback<List<VerItem>>() {
            @Override
            public void onResponse(Call<List<VerItem>> call, Response<List<VerItem>> response) {
                if (response.isSuccessful()) {
                    List<VerItem> productList = response.body();
                    if (productList != null) {
                        updateVerticalList(productList);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<VerItem>> call, Throwable t) {
                Toast.makeText(getActivity(), "Connection errors!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void updateVerticalList(List<VerItem> productList) {
        verItemList.clear();
        verItemList.addAll(productList);
        adapter_ver.notifyDataSetChanged();
    }

}
