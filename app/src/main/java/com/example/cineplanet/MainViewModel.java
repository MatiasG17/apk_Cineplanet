package com.example.cineplanet;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * ViewModel que centraliza estado compartido (título de header, franja promo y slides).
 */
public class MainViewModel extends ViewModel {

    private final MutableLiveData<String> headerTitle = new MutableLiveData<>();
    private final MutableLiveData<String> promoText = new MutableLiveData<>();
    private final MutableLiveData<List<SliderItem>> slides = new MutableLiveData<>();
    private final MutableLiveData<Integer> headerColor = new MutableLiveData<>();

    public MainViewModel() {
        headerTitle.setValue("");
        promoText.setValue("");
        headerColor.setValue(R.color.brand_blue);
        slides.setValue(buildDefaultSlides());
    }

    private List<SliderItem> buildDefaultSlides() {
        List<SliderItem> list = new ArrayList<>();
        list.add(new SliderItem(
                "https://image.tmdb.org/t/p/w780/5YZbUmjbMa3ClvSW1Wj3D6XGolb.jpg",
                "Guardianes de la Galaxia Vol. 3",
                "El final de la trilogía",
                "YA EN CINES"));
        list.add(new SliderItem(
                "https://image.tmdb.org/t/p/w780/9n2tJBplPbgR2ca05hS5CKXwP2c.jpg",
                "Super Mario Bros. La Película",
                "La aventura comienza",
                "ESTRENO"));
        list.add(new SliderItem(
                "https://image.tmdb.org/t/p/w780/4HodYYKEIsGOdinkGi2Ucz6X9i0.jpg",
                "Spider-Man: A través del Spider-Verso",
                "Un nuevo universo",
                "PRE-VENTA"));
        return list;
    }

    public LiveData<String> getHeaderTitle() {
        return headerTitle;
    }

    public LiveData<String> getPromoText() {
        return promoText;
    }

    public LiveData<List<SliderItem>> getSlides() {
        return slides;
    }

    public LiveData<Integer> getHeaderColor() {
        return headerColor;
    }

    public void setHeaderTitle(String title) {
        headerTitle.setValue(title);
    }

    public void setPromoText(String text) {
        promoText.setValue(text);
    }

    public void setHeaderColor(int colorRes) {
        headerColor.setValue(colorRes);
    }
}
