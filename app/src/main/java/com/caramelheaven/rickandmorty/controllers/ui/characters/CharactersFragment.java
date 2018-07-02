package com.caramelheaven.rickandmorty.controllers.ui.characters;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
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
import com.caramelheaven.rickandmorty.controllers.ui.BaseFragment;
import com.caramelheaven.rickandmorty.utils.AonItemClickListener;
import com.caramelheaven.rickandmorty.utils.AppUtil;
import com.caramelheaven.rickandmorty.utils.EndlessRecyclerViewScrollListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class CharactersFragment extends BaseFragment {

    private CharacterViewModel viewModel;
    private CharacterAdapter adapter;
    private LinearLayoutManager linearLayoutManager;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.networkStatus)
    TextView networkStatus;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    public static CharactersFragment newInstance() {
        Bundle args = new Bundle();
        CharactersFragment fragment = new CharactersFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_characters, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        viewModel = ViewModelProviders.of(this).get(CharacterViewModel.class);
        viewModel.init();

        setAdapterAndRecycler();
        observeCharacters();
    }

    @Override
    protected void setAdapterAndRecycler() {
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new CharacterAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        adapter.setAonItemClickListener(new AonItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getContext(), "pos: " + position, Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if (AppUtil.isNetworkConnectionAvailable()) {
                    page++;
                    Timber.d("next page: " + page);
                    viewModel.loadMorePagination(page++);
                } else {
                    Toast.makeText(getActivity(), "Network is not available", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void observeCharacters() {
        viewModel.getCharacters().observe(this, characters -> {
            if (!AppUtil.isNetworkConnectionAvailable() && characters.size() == 0) {
                networkStatus.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
            if (characters.size() != 0) {
                progressBar.setVisibility(View.GONE);
                adapter.updateAdapter(characters);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Timber.d("I'm deleted");
    }
}
