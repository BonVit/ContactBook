package com.vb.contactbook.di.module;

import android.content.Context;
import android.support.annotation.NonNull;

import com.vb.contactbook.mvp.model.database.DaoDatabase;
import com.vb.contactbook.mvp.model.database.IDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by bonar on 6/13/2017.
 */

@Module
public class DatabaseModule {
    @Provides
    @NonNull
    @Singleton
    IDatabase provideDatabase(Context context) {
        return new DaoDatabase(context);
    }
}
