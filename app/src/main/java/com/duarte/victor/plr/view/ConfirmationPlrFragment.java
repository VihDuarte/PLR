package com.duarte.victor.plr.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.duarte.victor.plr.R;
import com.duarte.victor.plr.interactor.PlrInteractorImpl;
import com.duarte.victor.plr.presenter.NewPlrPresenter;

import butterknife.BindView;
import butterknife.OnClick;

import static com.duarte.victor.plr.R.id.container;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConfirmationPlrFragment extends Fragment implements NewPlrView {
    private static String ARG_CREATOR = "MESSAGE";

    @BindView(R.id.progress)
    ProgressBar progressBar;

    @BindView(R.id.btn_post)
    Button btnPost;

    NewPlrPresenter presenter;

    String plrMessage;


    public static ConfirmationPlrFragment newInstance(String message) {
        ConfirmationPlrFragment confirmationPlrFragment = new ConfirmationPlrFragment();

        Bundle bundle = new Bundle();
        bundle.putString(ARG_CREATOR, message);

        confirmationPlrFragment.setArguments(bundle);
        return confirmationPlrFragment;
    }

    public ConfirmationPlrFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            plrMessage = getArguments().getString(ARG_CREATOR);
        }

        setRetainInstance(true);

        presenter = new NewPlrPresenter(new PlrInteractorImpl());
        presenter.setView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_confirmation_plr, container, false);
    }

    @OnClick(R.id.btn_post)
    public void onBtnPostClick() {
        presenter.postPlr(plrMessage);
    }

    @OnClick(R.id.txt_cancel)
    public void onCancelClick() {

    }

    @Override
    public void onSuccess() {
        Snackbar snackbar = Snackbar
                .make(getView(), R.string.create_plr_success, Snackbar.LENGTH_LONG)
                .setAction(R.string.undo, view -> {
                    presenter.deleteLasPlr();
                });

        snackbar.show();
    }

    @Override
    public void onError(int message) {
        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setMessage(message)
                .setPositiveButton(R.string.retry, (dialog, which) -> presenter.postPlr(plrMessage))
                .setNegativeButton(R.string.ok, (dialog, which) -> {
                })
                .setCancelable(false)
                .show();

        alertDialog.show();
    }

    @Override
    public void onDeleteErro(int message) {
        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setMessage(message)
                .setPositiveButton(R.string.retry, (dialog, which) -> presenter.deleteLasPlr())
                .setNegativeButton(R.string.ok, (dialog, which) -> {
                })
                .setCancelable(false)
                .show();

        alertDialog.show();
    }

    @Override
    public void showProgress() {
        btnPost.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        btnPost.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }
}
