package com.vb.contactbook.di.component;

import com.vb.contactbook.di.module.AppModule;
import com.vb.contactbook.mvp.presenter.MainPresenter;

import dagger.Component;

/**
 * Created by bonar on 6/13/2017.
 */

@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(MainPresenter mainPresenter);
}
