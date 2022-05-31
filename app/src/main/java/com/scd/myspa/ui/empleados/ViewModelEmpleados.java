package com.scd.myspa.ui.empleados;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewModelEmpleados extends ViewModel {

    private final MutableLiveData<String> mText;

    public ViewModelEmpleados() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}