package sample.com.flickrclient.data.model;

import java.util.List;

public class PhotosInfo {
    private int page;
    private int pages;
    private int perpage;
    private int total;
    private List<RecentPhotoInfo> photo;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPerpage() {
        return perpage;
    }

    public void setPerpage(int perpage) {
        this.perpage = perpage;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<RecentPhotoInfo> getPhoto() {
        return photo;
    }

    public void setPhoto(List<RecentPhotoInfo> photo) {
        this.photo = photo;
    }
}
