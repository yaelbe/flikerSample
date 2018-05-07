package sample.com.flickrclient.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import sample.com.flickrclient.ui.detail.ImageDetailActivity;
import sample.com.flickrclient.ui.feed.FeedActivity;

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract FeedActivity contributeFeedActivity();

    @ContributesAndroidInjector
    abstract ImageDetailActivity contributeImageDetailActivity();
}
