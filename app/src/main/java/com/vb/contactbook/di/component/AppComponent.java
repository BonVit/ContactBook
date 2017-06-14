package com.vb.contactbook.di.component;

import com.vb.contactbook.di.module.AppModule;
import com.vb.contactbook.di.module.DatabaseModule;
import com.vb.contactbook.di.module.GoogleModule;
import com.vb.contactbook.mvp.presenter.LoginPresenter;
import com.vb.contactbook.mvp.presenter.MainPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by bonar on 6/13/2017.
 */

@Component(modules = {AppModule.class, DatabaseModule.class, GoogleModule.class})
@Singleton
public interface AppComponent {
    //void inject(MainPresenter mainPresenter);
    void inject(LoginPresenter loginPresenter);
}
