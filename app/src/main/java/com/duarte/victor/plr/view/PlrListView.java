package com.duarte.victor.plr.view;

import com.duarte.victor.plr.model.Plr;

import java.util.List;

public interface PlrListView extends BaseView {
    void refresh();

    void addItems(List<Plr> items);

    void updateItems(List<Plr> items);

    void showError(int message, boolean isRefresh);
}
