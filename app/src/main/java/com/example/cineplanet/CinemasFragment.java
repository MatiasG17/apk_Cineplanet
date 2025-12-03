package com.example.cineplanet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CinemasFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cinemas, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recycler = view.findViewById(R.id.cinemasRecycler);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        List<CinemaItem> cinemas = new ArrayList<>();
        cinemas.add(new CinemaItem("Cp Alcazar", "Av. Santa Cruz 814-816"));
        cinemas.add(new CinemaItem("Cp Arequipa Mall Plaza", "Av. Ejercito 793 Cayma"));
        cinemas.add(new CinemaItem("Cp Arequipa Paseo Central", "Av. Arturo Ibañez S/N,"));
        cinemas.add(new CinemaItem("Cp Arequipa Real Plaza", "Av. Ejercito 1009 Cayma"));
        cinemas.add(new CinemaItem("Cp Brasil", "Av. Brasil 714 - 792 Piso 3"));

        recycler.setAdapter(new CinemasAdapter(cinemas));
    }
}
