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
import android.widget.EditText;
import android.widget.ProgressBar;

import com.duarte.victor.plr.R;
import com.duarte.victor.plr.interactor.PlrInteractorImpl;
import com.duarte.victor.plr.presenter.NewPlrPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewPlrFragment extends Fragment implements NewPlrView {
    @BindView(R.id.progress)
    ProgressBar progressBar;

    @BindView(R.id.edt_new_plr)
    EditText edtPlrMessage;

    @BindView(R.id.btn_post)
    Button btnPost;

    NewPlrPresenter presenter;

    public NewPlrFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
        presenter = new NewPlrPresenter(new PlrInteractorImpl());
        presenter.setView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_plr, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onSuccess() {
        edtPlrMessage.setText("");

        Snackbar snackbar = Snackbar
                .make(getView(), R.string.create_plr_success, Snackbar.LENGTH_LONG)
                .setAction(R.string.undo, view -> {
                    presenter.deleteLasPlr();
                });

        snackbar.show();
    }

    @OnClick(R.id.btn_post)
    public void onBtnPostClick() {
        presenter.postPlr(edtPlrMessage.getText().toString());
    }

    @Override
    public void onError(int message) {
        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setMessage(message)
                .setPositiveButton(R.string.retry, (dialog, which) -> presenter.postPlr(edtPlrMessage.getText().toString()))
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
        btnPost.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        btnPost.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }
}
