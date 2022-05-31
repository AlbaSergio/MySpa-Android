package com.scd.myspa.ui.productos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.scd.myspa.databinding.FragmentProductosBinding;

public class FragmentProductos extends Fragment {

private FragmentProductosBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        ViewModelProductos viewModelProductos =
                new ViewModelProvider(this).get(ViewModelProductos.class);

    binding = FragmentProductosBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

        final TextView textView = binding.textHome;
        viewModelProductos.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}