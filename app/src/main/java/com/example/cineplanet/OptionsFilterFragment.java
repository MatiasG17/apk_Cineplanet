package com.example.cineplanet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class OptionsFilterFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_options_filter, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Botón Cerrar
        ImageView btnClose = view.findViewById(R.id.btnCloseFilter);
        btnClose.setOnClickListener(v -> {
            getParentFragmentManager().popBackStack();
        });

        // Texto "Borrar Filtros"
        TextView btnClear = view.findViewById(R.id.btnClearFilters);
        btnClear.setOnClickListener(v -> {
            // Lógica para limpiar filtros (opcional por ahora)
        });

        // Botón "Filtrar"
        View btnApply = view.findViewById(R.id.btnApplyFilters);
        btnApply.setOnClickListener(v -> {
            // Aplicar filtros y cerrar
            getParentFragmentManager().popBackStack();
        });
    }
}
