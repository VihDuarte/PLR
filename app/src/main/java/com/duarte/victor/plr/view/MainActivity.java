package com.duarte.victor.plr.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;

import com.duarte.victor.plr.R;
import com.duarte.victor.plr.interactor.PlrInteractorImpl;
import com.duarte.victor.plr.presenter.NewPlrPresenter;
import com.duarte.victor.plr.presenter.PlrListPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, new PlrListFragment(), null)
                .commit();
    }


    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() != 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    public void changeFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_up,
                        R.anim.slide_down,
                        R.anim.slide_up,
                        R.anim.slide_down)
                .add(R.id.container, fragment)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }
}
