package com.example.myfarm.ui.dzialki;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DzialkiViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DzialkiViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Działki");
    }

    public LiveData<String> getText() {
        return mText;
    }
}