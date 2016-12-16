package com.duarte.victor.plr.view;


import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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

    @BindView(R.id.txt_count)
    TextView txtCount;

    @BindView(R.id.btn_post)
    Button btnPost;

    @BindView(R.id.img_logo)
    ImageView imgLogo;

    @BindView(R.id.txt_title)
    TextView txtTitle;

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
    public void onResume() {
        super.onResume();

        this.getView().getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener);

        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtPlrMessage.requestFocus();

        InputMethodManager imm = (InputMethodManager) getActivity()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInputFromWindow(edtPlrMessage.getWindowToken(),
                InputMethodManager.SHOW_FORCED, 0);
    }

    @Override
    public void onPause() {
        super.onPause();

        this.getView().getViewTreeObserver().removeOnGlobalLayoutListener(onGlobalLayoutListener);

        View view = getActivity().getCurrentFocus();

        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edtPlrMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtCount.setText(getString(R.string.create_plr_count_caracteres,
                        String.valueOf(140 - s.length())));
                btnPost.setEnabled(s.length() > 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
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
        if (edtPlrMessage.getText().toString().isEmpty()) {
            edtPlrMessage.setError(getContext().getResources().getString(R.string.required_field));
            return;
        }

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
        btnPost.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        btnPost.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.hardKeyboardHidden == Configuration.HARDKEYBOARDHIDDEN_NO) {
            imgLogo.setVisibility(View.GONE);
        } else if (newConfig.hardKeyboardHidden == Configuration.HARDKEYBOARDHIDDEN_YES) {
            imgLogo.setVisibility(View.VISIBLE);
        }
    }

    public ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        private int mPreviousHeight;

        @Override
        public void onGlobalLayout() {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                imgLogo.setVisibility(View.GONE);
                txtTitle.setVisibility(View.GONE);
            } else {
                int newHeight = getView().getHeight();
                if (mPreviousHeight != 0) {
                    if (mPreviousHeight > newHeight) {
                        // Height decreased: keyboard was shown
                        imgLogo.setVisibility(View.GONE);
                        txtTitle.setVisibility(View.GONE);
                    } else if (mPreviousHeight < newHeight) {
                        // Height increased: keyboard was hidden
                        imgLogo.setVisibility(View.VISIBLE);
                        txtTitle.setVisibility(View.VISIBLE);
                    } else {
                        // No change
                    }
                }
                mPreviousHeight = newHeight;
            }
        }
    };
}
