package com.example.cineplanet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

/**
 * Fragmento de inicio con slider de héroes.
 */
public class HomeFragment extends Fragment {

    private LinearLayout sliderDotsContainer;
    private ImageView[] sliderDots;
    private MainViewModel viewModel;
    private List<SliderItem> items = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        setupSlider(view);
    }

    private void setupSlider(View root) {
        ViewPager2 pager = root.findViewById(R.id.heroPager);
        sliderDotsContainer = root.findViewById(R.id.sliderDots);

        viewModel.getSlides().observe(getViewLifecycleOwner(), sliderItems -> {
            if (sliderItems == null || sliderItems.isEmpty()) return;
            items = sliderItems;
            SliderAdapter adapter = new SliderAdapter(items);
            pager.setAdapter(adapter);
            pager.setOffscreenPageLimit(items.size());
            setupDots(root, items.size());
            updatePromo(items.get(0).promoLabel);
        });

        pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                updateDots(position);
                if (position < items.size()) {
                    updatePromo(items.get(position).promoLabel);
                }
            }
        });
    }

    private void setupDots(View root, int count) {
        sliderDotsContainer.removeAllViews();
        sliderDots = new ImageView[count];

        for (int i = 0; i < count; i++) {
            ImageView dot = new ImageView(root.getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(6, 0, 6, 0);
            dot.setLayoutParams(params);
            dot.setImageDrawable(getDotDrawable(root, i == 0));
            sliderDotsContainer.addView(dot);
            sliderDots[i] = dot;
        }
    }

    private void updateDots(int position) {
        if (sliderDots == null) return;
        for (int i = 0; i < sliderDots.length; i++) {
            sliderDots[i].setImageDrawable(getDotDrawable(requireView(), i == position));
        }
    }

    private android.graphics.drawable.Drawable getDotDrawable(View root, boolean active) {
        @DrawableRes int res = active ? R.drawable.dot_active : R.drawable.dot_inactive;
        return ContextCompat.getDrawable(root.getContext(), res);
    }

    private void updatePromo(String label) {
        viewModel.setPromoText(label);
    }
}
