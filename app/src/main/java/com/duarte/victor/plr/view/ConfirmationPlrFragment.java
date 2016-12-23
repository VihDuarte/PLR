package com.duarte.victor.plr.view;


import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.duarte.victor.plr.R;
import com.duarte.victor.plr.interactor.PlrInteractorImpl;
import com.duarte.victor.plr.presenter.ConfirmationPlrPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConfirmationPlrFragment extends Fragment implements ConfirmationPlrView {
    private static String ARG_CREATOR = "MESSAGE";

    @BindView(R.id.progress)
    ProgressBar progressBar;

    @BindView(R.id.txt_time)
    TextView txtTime;

    @BindView(R.id.btn_post)
    Button btnPost;

    ConfirmationPlrPresenter presenter;

    String plrMessage;

    CountDownTimer countDownTimer;


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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_confirmation_plr, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            plrMessage = getArguments().getString(ARG_CREATOR);
        }


        View view = getActivity().getCurrentFocus();

        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        setRetainInstance(true);

        presenter = new ConfirmationPlrPresenter(new PlrInteractorImpl());
        presenter.setView(this);

        countDownTimer = new CountDownTimer(11000, 1000) {
            public void onTick(long millisUntilFinished) {
                txtTime.setText(millisUntilFinished / 1000 + "s");
            }

            public void onFinish() {
                presenter.postPlr(plrMessage);
            }
        };
        countDownTimer.start();
    }

    @OnClick(R.id.btn_post)
    public void onBtnPostClick() {
        presenter.postPlr(plrMessage);
        countDownTimer.cancel();
    }

    @OnClick(R.id.txt_cancel)
    public void onCancelClick() {
        countDownTimer.cancel();
        getActivity().onBackPressed();
    }

    @Override
    public void onSuccess() {
        getActivity().onBackPressed();

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
        btnPost.setEnabled(false);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        btnPost.setEnabled(true);
        progressBar.setVisibility(View.GONE);
    }
}
