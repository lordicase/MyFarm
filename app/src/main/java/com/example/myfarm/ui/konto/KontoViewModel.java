package com.example.myfarm.ui.konto;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class KontoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public KontoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Konto");
    }

    public LiveData<String> getText() {
        return mText;
    }
}