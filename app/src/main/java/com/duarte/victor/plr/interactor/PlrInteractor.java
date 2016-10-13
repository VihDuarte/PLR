package com.duarte.victor.plr.interactor;

import com.duarte.victor.plr.model.Plr;
import com.duarte.victor.plr.model.ServiceResponse;

import java.util.List;

import io.reactivex.Observable;


public interface PlrInteractor {
    Observable<List<Plr>> getPlrs(int page);

    Observable<String> postPlr(Plr plr);

    Observable<ServiceResponse> deletePlr(String idPlr);
}
