package com.scd.myspa.ui.empleados;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.scd.myspa.databinding.FragmentEmpleadosBinding;

public class FragmentEmpleados extends Fragment {

private FragmentEmpleadosBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        ViewModelEmpleados viewModelEmpleados =
                new ViewModelProvider(this).get(ViewModelEmpleados.class);

    binding = FragmentEmpleadosBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

        final TextView textView = binding.textSlideshow;
        viewModelEmpleados.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}