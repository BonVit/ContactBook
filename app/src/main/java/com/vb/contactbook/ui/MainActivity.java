package com.vb.contactbook.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.vb.contactbook.R;
import com.vb.contactbook.mvp.presenter.MainPresenter;
import com.vb.contactbook.mvp.view.MainView;
import com.vb.contactbook.utils.SharedPreferences;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bonar on 6/13/2017.
 */

public class MainActivity extends MvpAppCompatActivity implements MainView {
    public static final String TAG = "MainActivity";

    /*Moxy mvp binding*/
    @InjectPresenter
    MainPresenter mMainPresenter;

    /*ButterKnife view binding*/
    @BindView(R.id.fab)
    FloatingActionButton mFab;

    public static Intent newIntent(Context context) {
        Intent i = new Intent(context, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.activity_main_menu_log_out:
                mMainPresenter.logout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void startLoginActivity() {
        Intent i = LoginActivity.newIntent(this);
        SharedPreferences.setUserId(this, null);
        startActivity(i);
    }
}
