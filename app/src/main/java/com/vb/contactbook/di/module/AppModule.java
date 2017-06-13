package com.vb.contactbook.di.module;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by bonar on 6/13/2017.
 */

@Module
public class AppModule {
    @Provides
    @NonNull
    @Singleton
    Context provideContext() {
        return mContext;
    }

    private Context mContext;

    public AppModule(@NonNull Context context) {
        mContext = context;
    }
}
