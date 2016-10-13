package com.duarte.victor.plr.view;

import com.duarte.victor.plr.model.Plr;

import java.util.List;

public interface PlrListView extends BaseView {
    void addItems(List<Plr> items);
    void addItem(Plr item);
    void showError(int message);
}
