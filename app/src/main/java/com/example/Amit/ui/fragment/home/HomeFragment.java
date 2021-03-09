package com.example.Amit.ui.fragment.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.Amit.R;
import com.example.Amit.data.model.products.Product;
import com.example.Amit.helper.TokenManager;
import com.example.Amit.ui.adapter.product.recycler_adapter;
import com.example.Amit.ui.adapter.product.serviceProduct;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements serviceProduct {


    public HomeFragment() {
        // Required empty public constructor
    }
    RecyclerView productsRecycler;
    recycler_adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    NavController navController;
      homeViewModel hm;
    List<Product>models=new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_home, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController=Navigation.findNavController(view);

        intiRecycler(view);

        hm = new ViewModelProvider(requireActivity()).get(homeViewModel.class);
        hm.getProducts();
        hm.getProductLiveData().observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                adapter.setModelList(products);
            }
        });
        hm.getMessageLiveData().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getActivity(),""+s,Toast.LENGTH_LONG).show();
            }
        });
        hm.getAddCartResponseMutableLiveData().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getContext(),""+s,Toast.LENGTH_LONG).show();
            }
        });

        hm.getMessageAddCartLiveData().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getContext(),""+s,Toast.LENGTH_LONG).show();
            }
        });

    }
    protected void intiRecycler(View v){

        productsRecycler=v.findViewById(R.id.rec);
        adapter=new recycler_adapter(getContext(),this);

        layoutManager=new GridLayoutManager(getContext(),2);
        productsRecycler.setLayoutManager(layoutManager);

        productsRecycler.setAdapter(adapter);

    }
    @Override
    public void showDetails(Product product) {

        Bundle bundle= new Bundle();
        bundle.putSerializable("product", product);
        navController.navigate(R.id.action_home_item_to_details_Fragment,bundle);

    }

    @Override
    public void AddToCart(Product product) {

        TokenManager token =new TokenManager(getContext());
        String tokenid=token.getToken();

        hm.addProductsToCart(product.getId(),tokenid,1);
    }
}