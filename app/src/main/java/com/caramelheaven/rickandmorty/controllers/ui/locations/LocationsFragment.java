package com.caramelheaven.rickandmorty.controllers.ui.locations;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.caramelheaven.rickandmorty.R;
import com.caramelheaven.rickandmorty.datasourse.entity.location.Location;
import com.caramelheaven.rickandmorty.utils.AonItemClickListener;
import com.caramelheaven.rickandmorty.utils.AppUtil;
import com.caramelheaven.rickandmorty.utils.EndlessRecyclerViewScrollListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class LocationsFragment extends Fragment {

    private LocationViewModel viewModel;
    private LocationAdapter adapter;
    private LinearLayoutManager linearLayoutManager;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.networkStatus)
    TextView networkStatus;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    public static LocationsFragment newInstance() {

        Bundle args = new Bundle();

        LocationsFragment fragment = new LocationsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_locations, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        viewModel = ViewModelProviders.of(this).get(LocationViewModel.class);

        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new LocationAdapter(new ArrayList());
        recyclerView.setAdapter(adapter);

        adapter.setAonItemClickListener(new AonItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getContext(), "position: " + position, Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if (AppUtil.isNetworkConnectionAvailable()) {
                    page++;
                    viewModel.loadMorePagination(page);
                } else {
                    Toast.makeText(getActivity(), "Network is not available", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        observeLocations();
    }

    private void observeLocations() {
        viewModel.getLocations().observe(this, locations -> {
            Timber.d("size locations: " + locations.size());
            if (!AppUtil.isNetworkConnectionAvailable() && locations.size() == 0) {
                networkStatus.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
            if (locations.size() != 0) {
                for (Location location : locations) {
                    Timber.d("lications: " + location.getName());
                }
                progressBar.setVisibility(View.GONE);
                adapter.updateAdapter(locations);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Timber.d("I'm in onDestroy");
    }
}
