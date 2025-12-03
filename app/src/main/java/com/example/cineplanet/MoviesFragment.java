package com.example.cineplanet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MoviesFragment extends Fragment {

    private RecyclerView recycler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recycler = view.findViewById(R.id.moviesRecycler);
        recycler.setLayoutManager(new GridLayoutManager(getContext(), 2));

        // Configurar el click en "Ciudad"
        LinearLayout btnCity = view.findViewById(R.id.filterCityContainer);
        if (btnCity != null) {
            btnCity.setOnClickListener(v -> {
                // Navegar a CityFilterFragment con animación personalizada
                getParentFragmentManager().beginTransaction()
                        .setCustomAnimations(
                                R.anim.slide_in_up,
                                R.anim.slide_out_down,
                                R.anim.slide_in_up,
                                R.anim.slide_out_down
                        )
                        .replace(R.id.fragmentContainer, new CityFilterFragment())
                        .addToBackStack(null)
                        .commit();
            });
        }

        // Configurar el click en "Fecha"
        LinearLayout btnDate = view.findViewById(R.id.filterDateContainer);
        if (btnDate != null) {
            btnDate.setOnClickListener(v -> {
                // Navegar a DateFilterFragment con animación personalizada
                getParentFragmentManager().beginTransaction()
                        .setCustomAnimations(
                                R.anim.slide_in_up,
                                R.anim.slide_out_down,
                                R.anim.slide_in_up,
                                R.anim.slide_out_down
                        )
                        .replace(R.id.fragmentContainer, new DateFilterFragment())
                        .addToBackStack(null)
                        .commit();
            });
        }

        // Configurar el click en "Opciones"
        LinearLayout btnOptions = view.findViewById(R.id.filterOptionsContainer);
        if (btnOptions != null) {
            btnOptions.setOnClickListener(v -> {
                // Navegar a OptionsFilterFragment con animación personalizada
                getParentFragmentManager().beginTransaction()
                        .setCustomAnimations(
                                R.anim.slide_in_up,
                                R.anim.slide_out_down,
                                R.anim.slide_in_up,
                                R.anim.slide_out_down
                        )
                        .replace(R.id.fragmentContainer, new OptionsFilterFragment())
                        .addToBackStack(null)
                        .commit();
            });
        }

        setupTabs(view);
        // Cargar "En Cartelera" por defecto
        loadMovies(0);
    }

    private void setupTabs(View root) {
        TabLayout tabLayout = root.findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("En Cartelera"));
        tabLayout.addTab(tabLayout.newTab().setText("Preventa"));
        tabLayout.addTab(tabLayout.newTab().setText("Próximos Estrenos"));
        // tabLayout.addTab(tabLayout.newTab().setText("The Twist"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                loadMovies(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    private void loadMovies(int tabIndex) {
        List<MovieItem> movies = new ArrayList<>();

        if (tabIndex == 0) { // En Cartelera - Películas populares recientes
            movies.add(new MovieItem("https://image.tmdb.org/t/p/w500/ulzhLuWrPK07P1YkdWQLZnQh1JL.jpg", "Avengers: Endgame", "Estreno"));
            movies.add(new MovieItem("https://image.tmdb.org/t/p/w500/t6HIqrRAclMCA60NsSmeqe9RmNV.jpg", "Avatar: The Way of Water", "Estreno"));
            movies.add(new MovieItem("https://image.tmdb.org/t/p/w500/uJYYizSuA9Y3DCs0qS4qWvHfZg4.jpg", "Spider-Man: No Way Home", "Estreno"));
            movies.add(new MovieItem("https://image.tmdb.org/t/p/w500/1NqwE6LP9IEdOZ57WruG03iseHr.jpg", "Puss in Boots", "Estreno"));
        } else if (tabIndex == 1) { // Preventa - Películas de acción/héroes
            movies.add(new MovieItem("https://image.tmdb.org/t/p/w500/vZloFAK7NmvMGKE7VkF5UHaz0I.jpg", "John Wick 4", "Preventa"));
            movies.add(new MovieItem("https://image.tmdb.org/t/p/w500/wRnbWt44nKjsFPrqSmwYki5vZtF.jpg", "Doctor Strange", "Preventa"));
            movies.add(new MovieItem("https://image.tmdb.org/t/p/w500/pIkRyD18kl4FhoCNQuWxWu5cBLM.jpg", "Thor: Love and Thunder", "Preventa"));
            movies.add(new MovieItem("https://image.tmdb.org/t/p/w500/sv1xJUazXeYqALzczSZ3O6nkH75.jpg", "Black Panther", "Preventa"));
        } else if (tabIndex == 2) { // Próximos Estrenos - Animadas / Familiares
            movies.add(new MovieItem("https://image.tmdb.org/t/p/w500/4j0PNHkMr5ax3IA8tjtxcmPU3QT.jpg", "Encanto", "Próximamente"));
            movies.add(new MovieItem("https://image.tmdb.org/t/p/w500/kDp1vUBnMpe8ak4rjgl3cLELqjU.jpg", "Kung Fu Panda 4", "Próximamente"));
            movies.add(new MovieItem("https://image.tmdb.org/t/p/w500/qdfARIhgpgZOBh3vfNhWS4hmSo3.jpg", "Frozen II", "Próximamente"));
            movies.add(new MovieItem("https://image.tmdb.org/t/p/w500/g0OWgCsVvQJbN0xV0nJb7o7N4X.jpg", "Ant-Man & The Wasp: Quantumania", "Próximamente"));
        }
        
        recycler.setAdapter(new MoviesAdapter(movies));
    }
}
