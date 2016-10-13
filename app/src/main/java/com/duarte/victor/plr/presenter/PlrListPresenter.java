package com.duarte.victor.plr.presenter;

import com.duarte.victor.plr.R;
import com.duarte.victor.plr.interactor.PlrInteractor;
import com.duarte.victor.plr.view.PlrListView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class PlrListPresenter {
    private PlrListView view;
    private int currentPage = 0;
    private PlrInteractor interactor;

    public PlrListPresenter(PlrInteractor interactor) {
        this.interactor = interactor;
    }

    public void setView(PlrListView view) {
        this.view = view;
    }

    public void loadPlrs(boolean refresh) {
        if (view != null) {
            view.showProgress();

            if (refresh)
                currentPage = 0;

            interactor.getPlrs(currentPage++)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(result -> {
                                if (refresh)
                                    view.updateItems(result);
                                else
                                    view.addItems(result);

                                view.hideProgress();
                            },
                            error -> {
                                currentPage--;
                                view.showError(refresh ? R.string.refresh_plr_error : R.string.request_plr_error, refresh);
                                view.hideProgress();
                            });
        }
    }
}

