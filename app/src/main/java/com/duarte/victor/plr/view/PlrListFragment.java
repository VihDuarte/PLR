package com.duarte.victor.plr.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.duarte.victor.plr.R;
import com.duarte.victor.plr.interactor.PlrInteractorImpl;
import com.duarte.victor.plr.model.Plr;
import com.duarte.victor.plr.presenter.PlrListPresenter;
import com.duarte.victor.plr.view.adapter.PlrListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlrListFragment extends Fragment implements PlrListView {
    @BindView(R.id.recycler_plr_list)
    RecyclerView plrListRecicler;

    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.fab_new)
    FloatingActionButton fabNew;

    PlrListPresenter presenter;

    private ArrayList<Plr> plrList;
    private PlrListAdapter plrListAdapter;

    public PlrListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plr_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
        presenter = new PlrListPresenter(new PlrInteractorImpl());
        presenter.setView(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView();

        if (plrList == null || plrList.size() == 0) {
            presenter.loadPlrs(false);
        } else {
            plrListAdapter = new PlrListAdapter(getContext(), plrList);
            plrListRecicler.setAdapter(plrListAdapter);
        }


        swipeRefreshLayout.setOnRefreshListener(() -> presenter.loadPlrs(true));
    }

    @Override
    public void addItems(List<Plr> items) {
        if (plrListAdapter == null) {
            plrList = (ArrayList<Plr>) items;
            plrListAdapter = new PlrListAdapter(getContext(), items);
            plrListRecicler.setAdapter(plrListAdapter);
        } else {
            plrList.addAll(items);
            plrListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void updateItems(List<Plr> items) {
        if (plrListAdapter != null) {
            plrList.clear();
            plrList.addAll(items);
            plrListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showError(int message, boolean isRefresh) {
        Snackbar snackbar = Snackbar
                .make(getView(), message, Snackbar.LENGTH_SHORT)
                .setAction(R.string.retry, view -> {
                    presenter.loadPlrs(isRefresh);
                });

        snackbar.show();
    }

    @Override
    public void showProgress() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        swipeRefreshLayout.setRefreshing(false);
    }

    private void initRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());

        plrListRecicler.setHasFixedSize(true);
        plrListRecicler.setLayoutManager(layoutManager);

        plrListRecicler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (((LinearLayoutManager) layoutManager)
                        .findLastCompletelyVisibleItemPosition() == layoutManager.getItemCount() - 1) {
                    presenter.loadPlrs(false);
                }

                if (dy > 0 || dy < 0 && fabNew.isShown())
                    fabNew.hide();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    fabNew.show();
                }
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
    }

    @OnClick(R.id.fab_new)
    public void openNewPlrFragment() {
        ((MainActivity) getActivity()).changeFragment(new NewPlrFragment());
    }
}
