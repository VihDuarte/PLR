package com.duarte.victor.plr.interactor;

import com.duarte.victor.plr.ServiceFactory;
import com.duarte.victor.plr.model.Plr;

import java.util.List;

import io.reactivex.Observable;

public class PlrInteractorImpl implements PlrInteractor {
    @Override
    public Observable<List<Plr>> getPlrs(int page) {
        return ServiceFactory.getService().getPlrs(page);
    }

    @Override
    public Observable<String> postPlr(Plr plr) {
        return ServiceFactory.getService().postPlr(plr);
    }

    @Override
    public Observable<Integer> deletePlr(String idPlr) {
        return ServiceFactory.getService().deletePlr(idPlr);
    }
}
