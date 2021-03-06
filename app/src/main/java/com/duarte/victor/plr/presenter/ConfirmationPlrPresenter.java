package com.duarte.victor.plr.presenter;

import com.duarte.victor.plr.R;
import com.duarte.victor.plr.interactor.PlrInteractor;
import com.duarte.victor.plr.model.Plr;
import com.duarte.victor.plr.view.ConfirmationPlrView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ConfirmationPlrPresenter {
    private ConfirmationPlrView view;
    private PlrInteractor interactor;
    private String lastPlrId;

    public ConfirmationPlrPresenter(PlrInteractor interactor) {
        this.interactor = interactor;
    }

    public void setView(ConfirmationPlrView view) {
        this.view = view;
    }

    public void postPlr(String text) {
        view.showProgress();

        Plr plr = new Plr();
        plr.setText(text);

        interactor.postPlr(plr)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                            lastPlrId = result;
                            view.onSuccess();
                            view.hideProgress();
                        },
                        error -> {
                            view.onError(R.string.create_plr_error);
                            view.hideProgress();
                        });
    }

    public void deleteLasPlr() {
        view.showProgress();

        interactor.deletePlr(lastPlrId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                            view.hideProgress();
                            if (result != 1) {
                                view.onDeleteError(R.string.delete_plr_error);
                            }
                        },
                        error -> {
                            view.onDeleteError(R.string.delete_plr_error);
                            view.hideProgress();
                        });
    }
}
