// Generated code from Butter Knife. Do not modify!
package com.duarte.victor.plr.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.duarte.victor.plr.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PlrListFragment_ViewBinding<T extends PlrListFragment> implements Unbinder {
  protected T target;

  @UiThread
  public PlrListFragment_ViewBinding(T target, View source) {
    this.target = target;

    target.plrListRecicler = Utils.findRequiredViewAsType(source, R.id.recycler_plr_list, "field 'plrListRecicler'", RecyclerView.class);
    target.progressBar = Utils.findRequiredViewAsType(source, R.id.progress, "field 'progressBar'", ProgressBar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.plrListRecicler = null;
    target.progressBar = null;

    this.target = null;
  }
}
