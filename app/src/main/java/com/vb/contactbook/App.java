package com.vb.contactbook;

import android.app.Application;

import com.vb.contactbook.di.component.AppComponent;
import com.vb.contactbook.di.component.DaggerAppComponent;
import com.vb.contactbook.di.module.AppModule;
import com.vb.contactbook.di.module.DatabaseModule;

/**
 * Created by bonar on 6/13/2017.
 */

public class App extends Application {
    private static final String TAG = "App";

    private static AppComponent mAppComponent;

    public static AppComponent getAppComponent() {
        return mAppComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .databaseModule(new DatabaseModule())
                .build();
    }
}
