// Generated code from Butter Knife. Do not modify!
package com.duarte.victor.plr.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.duarte.victor.plr.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class NewPlrFragment_ViewBinding<T extends NewPlrFragment> implements Unbinder {
  protected T target;

  private View view2131492989;

  @UiThread
  public NewPlrFragment_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.edtPlrMessage = Utils.findRequiredViewAsType(source, R.id.edt_new_plr, "field 'edtPlrMessage'", EditText.class);
    target.txtCount = Utils.findRequiredViewAsType(source, R.id.txt_count, "field 'txtCount'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btn_post, "field 'btnPost' and method 'onBtnPostClick'");
    target.btnPost = Utils.castView(view, R.id.btn_post, "field 'btnPost'", Button.class);
    view2131492989 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onBtnPostClick();
      }
    });
    target.imgLogo = Utils.findRequiredViewAsType(source, R.id.img_logo, "field 'imgLogo'", ImageView.class);
    target.txtTitle = Utils.findRequiredViewAsType(source, R.id.txt_title, "field 'txtTitle'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.edtPlrMessage = null;
    target.txtCount = null;
    target.btnPost = null;
    target.imgLogo = null;
    target.txtTitle = null;

    view2131492989.setOnClickListener(null);
    view2131492989 = null;

    this.target = null;
  }
}
