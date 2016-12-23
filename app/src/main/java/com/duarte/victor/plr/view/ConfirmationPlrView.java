package com.duarte.victor.plr.view;

public interface ConfirmationPlrView extends BaseView {
    void onSuccess();

    void onError(int message);

    void onDeleteError(int message);
}
