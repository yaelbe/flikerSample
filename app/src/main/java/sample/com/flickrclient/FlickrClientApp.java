package sample.com.flickrclient;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import sample.com.flickrclient.di.DaggerAppComponent;


public class FlickrClientApp extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().create(this);
    }
}
