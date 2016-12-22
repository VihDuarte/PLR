// Generated code from Butter Knife. Do not modify!
package com.duarte.victor.plr.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.duarte.victor.plr.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ConfirmationPlrFragment_ViewBinding<T extends ConfirmationPlrFragment> implements Unbinder {
  protected T target;

  private View view2131492989;

  private View view2131492990;

  @UiThread
  public ConfirmationPlrFragment_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.progressBar = Utils.findRequiredViewAsType(source, R.id.progress, "field 'progressBar'", ProgressBar.class);
    view = Utils.findRequiredView(source, R.id.btn_post, "field 'btnPost' and method 'onBtnPostClick'");
    target.btnPost = Utils.castView(view, R.id.btn_post, "field 'btnPost'", Button.class);
    view2131492989 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onBtnPostClick();
      }
    });
    view = Utils.findRequiredView(source, R.id.txt_cancel, "method 'onCancelClick'");
    view2131492990 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onCancelClick();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.progressBar = null;
    target.btnPost = null;

    view2131492989.setOnClickListener(null);
    view2131492989 = null;
    view2131492990.setOnClickListener(null);
    view2131492990 = null;

    this.target = null;
  }
}
