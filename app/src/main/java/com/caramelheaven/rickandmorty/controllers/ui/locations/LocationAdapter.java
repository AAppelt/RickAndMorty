package com.caramelheaven.rickandmorty.controllers.ui.locations;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.caramelheaven.rickandmorty.R;
import com.caramelheaven.rickandmorty.datasourse.entity.location.Location;
import com.caramelheaven.rickandmorty.utils.AonItemClickListener;
import com.caramelheaven.rickandmorty.utils.LocationDiffCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LocationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Location> locationList;
    private AonItemClickListener aonItemClickListener;

    public LocationAdapter(List<Location> locationList) {
        this.locationList = locationList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_location, parent, false);
        return new LocationVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        LocationVH locationVH = (LocationVH) holder;
        locationVH.locationName.setText("Location: " + locationList.get(position).getName());
        locationVH.type.setText("Type: " + locationList.get(position).getType());
        locationVH.dimension.setText("Dimension: " + locationList.get(position).getDimension());
    }

    public void updateAdapter(List<Location> list) {
        final LocationDiffCallback diffCallback = new LocationDiffCallback(this.locationList, list);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.locationList.clear();
        this.locationList.addAll(list);
        diffResult.dispatchUpdatesTo(this);
    }

    @Override
    public int getItemCount() {
        return locationList.size();
    }

    class LocationVH extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tv_dimension)
        TextView dimension;

        @BindView(R.id.tv_location_name)
        TextView locationName;

        @BindView(R.id.tv_type)
        TextView type;

        @BindView(R.id.cardView)
        CardView cardView;

        public LocationVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            cardView.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View v) {
            aonItemClickListener.onItemClick(v, getAdapterPosition());
        }
    }

    public void setAonItemClickListener(AonItemClickListener aonItemClickListener) {
        this.aonItemClickListener = aonItemClickListener;
    }
}
