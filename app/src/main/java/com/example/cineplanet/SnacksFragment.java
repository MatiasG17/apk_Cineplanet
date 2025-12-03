package com.example.cineplanet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.ArrayList;
import java.util.List;

public class SnacksFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_snacks, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView banner = view.findViewById(R.id.snacksBanner);
        Spinner spinner = view.findViewById(R.id.cinemaSpinner);

        // Cargar imagen de banner similar a la referencia (Combo de cine)
        Glide.with(this)
                .load("https://images.unsplash.com/photo-1585647347384-2593bc35786b?auto=format&fit=crop&w=1000&q=80") 
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerCrop()
                .into(banner);

        // Configurar Spinner
        List<String> cinemas = new ArrayList<>();
        cinemas.add("Seleccione...");
        cinemas.add("Cp Alcazar");
        cinemas.add("Cp Arequipa");
        cinemas.add("Cp Brasil");
        cinemas.add("Cp Arequipa Mall Plaza");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, cinemas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}
