package com.duarte.victor.plr.view;

public interface NewPlrView extends BaseView {
    void onSuccess();

    void onError(int message);

    void onDeleteErro(int message);
}
