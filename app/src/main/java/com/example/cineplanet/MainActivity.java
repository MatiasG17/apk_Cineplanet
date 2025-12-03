package com.example.cineplanet;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/**
 * Pantalla principal con header, franja promocional, slider y barra inferior.
 */
public class MainActivity extends AppCompatActivity {

    private List<LinearLayout> navItems = new ArrayList<>();
    private TextView headerTitle;
    private TextView promoText;
    private View headerContainer;
    private View promoStrip;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        headerTitle = findViewById(R.id.headerTitle);
        promoText = findViewById(R.id.promoText);
        headerContainer = findViewById(R.id.headerContainer);
        promoStrip = findViewById(R.id.promoStrip);
        promoText.setText(""); // la franja se actualizará desde el slider

        setupBottomNav();
        setActiveNav(R.id.navHome);
        observeViewModel();
    }

    private void setupBottomNav() {
        LinearLayout navHome = findViewById(R.id.navHome);
        LinearLayout navMovies = findViewById(R.id.navMovies);
        LinearLayout navCinemas = findViewById(R.id.navCinemas);
        LinearLayout navSnacks = findViewById(R.id.navSnacks);
        LinearLayout navMore = findViewById(R.id.navMore);

        navItems = Arrays.asList(navHome, navMovies, navCinemas, navSnacks, navMore);
        for (LinearLayout nav : navItems) {
            nav.setOnClickListener(view -> setActiveNav(nav.getId()));
        }
    }

    private void setActiveNav(int activeId) {
        for (LinearLayout nav : navItems) {
            boolean active = nav.getId() == activeId;
            ImageView icon = (ImageView) nav.getChildAt(0);
            TextView label = (TextView) nav.getChildAt(1);
            int color = ContextCompat.getColor(this, active ? R.color.brand_blue : R.color.text_secondary);
            icon.setColorFilter(color);
            label.setTextColor(color);
        }
        switchSection(activeId);
    }

    private void switchSection(int activeId) {
        Fragment target;
        String title;
        int colorRes;
        boolean showPromo = false;

        if (activeId == R.id.navHome) {
            target = new HomeFragment();
            title = getString(R.string.header_home);
            colorRes = R.color.white;
            showPromo = true;
        } else if (activeId == R.id.navMovies) {
            target = new MoviesFragment();
            title = getString(R.string.header_movies);
            colorRes = R.color.white;
        } else if (activeId == R.id.navCinemas) {
            target = new CinemasFragment();
            title = getString(R.string.header_cinemas);
            colorRes = R.color.white;
        } else if (activeId == R.id.navSnacks) {
            target = new SnacksFragment();
            title = getString(R.string.header_snacks);
            colorRes = R.color.white;
        } else {
            target = new MoreFragment();
            title = getString(R.string.header_more);
            colorRes = R.color.white;
        }

        setHeaderTitle(title);
        setHeaderColor(colorRes);
        setPromoVisibility(showPromo);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, target);
        transaction.commit();
    }

    private void setHeaderTitle(String title) {
        viewModel.setHeaderTitle(title);
    }

    private void setHeaderColor(int colorRes) {
        viewModel.setHeaderColor(colorRes);
    }

    private void setPromoVisibility(boolean visible) {
        if (promoStrip != null) {
            promoStrip.setVisibility(visible ? View.VISIBLE : View.GONE);
        }
    }

    private void observeViewModel() {
        viewModel.getHeaderTitle().observe(this, headerTitle::setText);
        viewModel.getPromoText().observe(this, promoText::setText);
        viewModel.getHeaderColor().observe(this, colorRes -> {
            if (colorRes != null && headerContainer != null) {
                headerContainer.setBackgroundColor(ContextCompat.getColor(this, colorRes));
            }
        });
    }
}
