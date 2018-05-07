package sample.com.flickrclient.ui.feed;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import sample.com.flickrclient.data.PageItemData;
import sample.com.flickrclient.data.model.RecentPhotoInfo;
import sample.com.flickrclient.data.repository.FeedRepository;
import sample.com.flickrclient.util.RxViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class FeedViewModel extends RxViewModel {

    private static final int INITIAL_PAGE = 0;

    private int currentPage = INITIAL_PAGE;

    private final FeedRepository feedRepository;

    private MutableLiveData<List<RecentPhotoInfo>> photosInfoLiveData = new MutableLiveData<>();

    private MutableLiveData<Boolean> isLoadingLiveData = new MutableLiveData<>();

    private String filter = "";

    @Inject
    public FeedViewModel(FeedRepository feedRepository) {
        this.feedRepository = feedRepository;
        isLoadingLiveData.setValue(true);
        loadNextPage();
    }

    public void loadNextPage() {
        currentPage++;
        isLoadingLiveData.setValue(true);
        disposable.add(feedRepository.getCurrentPhotos(currentPage,filter)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::addPhoto));
    }

    public void setFilter(String filter){
        this.filter = filter;
        //reset
        currentPage = 0;
        photosInfoLiveData.postValue(new ArrayList<>());
        loadNextPage();
    }

    public LiveData<List<RecentPhotoInfo>> getPhotos() {
        return photosInfoLiveData;
    }

    public LiveData<Boolean> getIsLoading(){
        return isLoadingLiveData;
    }

    private void addPhoto(PageItemData<RecentPhotoInfo> recentPhotoInfo) {
        final List<RecentPhotoInfo> list = new ArrayList<>();
        if (photosInfoLiveData.getValue() != null) {
            list.addAll(photosInfoLiveData.getValue());
        }
        list.add(recentPhotoInfo.getData());
        photosInfoLiveData.setValue(list);

        if (recentPhotoInfo.getStatus() == PageItemData.Status.COMPLETE) {
            isLoadingLiveData.setValue(false);
        }
    }
}
