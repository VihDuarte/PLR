// Generated code from Butter Knife. Do not modify!
package com.duarte.victor.plr.view.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.duarte.victor.plr.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PlrListAdapter$ViewHolder_ViewBinding<T extends PlrListAdapter.ViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public PlrListAdapter$ViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.TxPlrItemText = Utils.findRequiredViewAsType(source, R.id.txt_plr_item_text, "field 'TxPlrItemText'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.TxPlrItemText = null;

    this.target = null;
  }
}
