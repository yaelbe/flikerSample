package sample.com.flickrclient.data;

import sample.com.flickrclient.data.model.RecentPhotoInfo;
import io.reactivex.Flowable;

public interface FeedDataSource {

    Flowable<PageItemData<RecentPhotoInfo>> getCurrentPhotos(int page ,String filter);

}
