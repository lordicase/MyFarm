package com.example.myfarm.ui.zabiegi;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ZabiegiViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ZabiegiViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Zabiegi");
    }

    public LiveData<String> getText() {
        return mText;
    }
}