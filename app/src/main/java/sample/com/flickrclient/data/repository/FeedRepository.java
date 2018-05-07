package sample.com.flickrclient.data.repository;

import javax.inject.Inject;
import javax.inject.Named;

import sample.com.flickrclient.data.FeedDataSource;
import sample.com.flickrclient.data.PageItemData;
import sample.com.flickrclient.data.model.RecentPhotoInfo;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

public class FeedRepository implements FeedDataSource {

    private final FeedDataSource feedDataSource;

    @Inject
    public FeedRepository(@Named("remote") FeedDataSource feedDataSource) {
        this.feedDataSource = feedDataSource;
    }

    @Override
    public Flowable<PageItemData<RecentPhotoInfo>> getCurrentPhotos(int page, String filter) {
        return feedDataSource.getCurrentPhotos(page, filter)
                .subscribeOn(Schedulers.io());
    }

}
