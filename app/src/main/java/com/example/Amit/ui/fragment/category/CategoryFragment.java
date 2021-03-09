package com.example.Amit.ui.fragment.category;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.Amit.R;
import com.example.Amit.data.model.category.CategoriesItem;
import com.example.Amit.ui.adapter.categories.Categories_adapter;

import java.util.List;


public class CategoryFragment extends Fragment {



    public CategoryFragment() {
        // Required empty public constructor
    }

    CategoryViewModel categorymodel;


    RecyclerView categoriesRecycle;
    Categories_adapter adapter2;
    RecyclerView.LayoutManager layoutManager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false);
    }
    protected void intiRecycler(View v){

        categoriesRecycle=v.findViewById(R.id.categories_recycler_view);
        adapter2=new Categories_adapter(getContext());

        layoutManager=new GridLayoutManager(getContext(),2);
        categoriesRecycle.setLayoutManager(layoutManager);

        categoriesRecycle.setAdapter(adapter2);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        intiRecycler(view);
        categorymodel = new ViewModelProvider(requireActivity()).get(CategoryViewModel.class);
        categorymodel.getAllCategories();
        categorymodel.getCategoryLiveData().observe(getViewLifecycleOwner(), new Observer<List<CategoriesItem>>() {
            @Override
            public void onChanged(List<CategoriesItem> categoriesItems) {
                adapter2.setModelList(categoriesItems);

            }
        });
        categorymodel.getMessageLiveData().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getActivity(),""+s,Toast.LENGTH_LONG).show();
            }
        });
    }
}