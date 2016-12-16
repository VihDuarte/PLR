// Generated code from Butter Knife. Do not modify!
package com.duarte.victor.plr.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.duarte.victor.plr.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PlrListFragment_ViewBinding<T extends PlrListFragment> implements Unbinder {
  protected T target;

  private View view2131492995;

  @UiThread
  public PlrListFragment_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.plrListRecicler = Utils.findRequiredViewAsType(source, R.id.recycler_plr_list, "field 'plrListRecicler'", RecyclerView.class);
    target.swipeRefreshLayout = Utils.findRequiredViewAsType(source, R.id.swiperefresh, "field 'swipeRefreshLayout'", SwipeRefreshLayout.class);
    view = Utils.findRequiredView(source, R.id.fab_new, "field 'fabNew' and method 'openNewPlrFragment'");
    target.fabNew = Utils.castView(view, R.id.fab_new, "field 'fabNew'", FloatingActionButton.class);
    view2131492995 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.openNewPlrFragment();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.plrListRecicler = null;
    target.swipeRefreshLayout = null;
    target.fabNew = null;

    view2131492995.setOnClickListener(null);
    view2131492995 = null;

    this.target = null;
  }
}
