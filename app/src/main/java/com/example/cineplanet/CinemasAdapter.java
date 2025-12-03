package com.example.cineplanet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CinemasAdapter extends RecyclerView.Adapter<CinemasAdapter.CinemaViewHolder> {

    private final List<CinemaItem> cinemas;

    public CinemasAdapter(List<CinemaItem> cinemas) {
        this.cinemas = cinemas;
    }

    @NonNull
    @Override
    public CinemaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cinema, parent, false);
        return new CinemaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CinemaViewHolder holder, int position) {
        holder.bind(cinemas.get(position));
    }

    @Override
    public int getItemCount() {
        return cinemas.size();
    }

    static class CinemaViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameView;
        private final TextView addressView;

        CinemaViewHolder(@NonNull View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.cinemaName);
            addressView = itemView.findViewById(R.id.cinemaAddress);
        }

        void bind(CinemaItem cinema) {
            nameView.setText(cinema.name);
            addressView.setText(cinema.address);
        }
    }
}
