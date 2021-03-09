package com.example.Amit.ui.fragment.category;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.Amit.data.model.category.CategoriesItem;
import com.example.Amit.data.repisotory.categoryRepisotory;

import java.util.List;

public class CategoryViewModel extends ViewModel {
    private LiveData<List<CategoriesItem>> categoryLiveData;
    private LiveData<String> messageLiveData ;

    public LiveData<List<CategoriesItem>> getCategoryLiveData() {
        return categoryLiveData;
    }

    public LiveData<String> getMessageLiveData() {
        return messageLiveData;
    }



    private categoryRepisotory categoryRepisotory = new categoryRepisotory();

    public void getAllCategories(){
        categoryRepisotory.getAllCategories();
        categoryLiveData=categoryRepisotory.getcategoryMutableLiveData();
        messageLiveData = categoryRepisotory.getMessageMutableLiveData();
    }
}
