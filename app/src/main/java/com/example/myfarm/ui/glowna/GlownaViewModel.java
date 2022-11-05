package com.example.myfarm.ui.glowna;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GlownaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public GlownaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Główna");
    }

    public LiveData<String> getText() {
        return mText;
    }
}