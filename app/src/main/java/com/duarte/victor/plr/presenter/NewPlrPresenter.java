package com.duarte.victor.plr.presenter;

import com.duarte.victor.plr.R;
import com.duarte.victor.plr.interactor.PlrInteractor;
import com.duarte.victor.plr.model.Plr;
import com.duarte.victor.plr.view.NewPlrView;
import com.duarte.victor.plr.view.PlrListView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NewPlrPresenter {
    private NewPlrView view;
    private PlrInteractor interactor;
    private String lastPlrId;

    public NewPlrPresenter(PlrInteractor interactor) {
        this.interactor = interactor;
    }

    public void setView(NewPlrView view) {
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
                            if (result.getCode() != 200) {
                                view.onDeleteErro(R.string.delete_plr_error);
                            }
                        },
                        error -> {
                            view.onDeleteErro(R.string.delete_plr_error);
                            view.hideProgress();
                        });
    }
}
