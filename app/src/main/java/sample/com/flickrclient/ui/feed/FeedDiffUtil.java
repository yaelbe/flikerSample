package sample.com.flickrclient.ui.feed;

import android.support.v7.util.DiffUtil;

import java.util.List;

import sample.com.flickrclient.data.model.RecentPhotoInfo;

public final class FeedDiffUtil extends DiffUtil.Callback {

    private List<RecentPhotoInfo> oldList;
    private List<RecentPhotoInfo> newList;

    public FeedDiffUtil(List<RecentPhotoInfo> oldList, List<RecentPhotoInfo> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getId().equals(newList.get(newItemPosition).getId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getId().equals(newList.get(newItemPosition).getId());
    }
}
