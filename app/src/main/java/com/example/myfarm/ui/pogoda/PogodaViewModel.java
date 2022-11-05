package com.example.myfarm.ui.pogoda;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PogodaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PogodaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Pogoda");
    }

    public LiveData<String> getText() {
        return mText;
    }
}