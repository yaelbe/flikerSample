package sample.com.flickrclient.util.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class InfiniteRecyclerView extends RecyclerView {

    public interface PageListener {
        void onListEnd();
    }

    private int pastVisibleItem, visibleItemCount, totalItemCount;
    private boolean isLoading = false;
    private PageListener pageListener;

    public InfiniteRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayoutManager(new GridLayoutManager(context,2));
        init();
    }

    private void init() {
        addOnScrollListener(getOnScrollListener());
    }

    public void setPageListener(PageListener pageListener) {
        this.pageListener = pageListener;
    }

    public void setLoading(boolean isLoading) {
        this.isLoading = isLoading;
    }

    private RecyclerView.OnScrollListener getOnScrollListener() {
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                GridLayoutManager lm = (GridLayoutManager) recyclerView.getLayoutManager();

                if (dy > 0) {
                    visibleItemCount = lm.getChildCount();
                    totalItemCount = lm.getItemCount();
                    pastVisibleItem = lm.findFirstVisibleItemPosition();
                    if (!isLoading) {
                        if ((visibleItemCount + pastVisibleItem) >= totalItemCount) {
                            isLoading = true;
                            if (pageListener != null) {
                                pageListener.onListEnd();
                            }
                        }
                    }
                }
            }
        };
    }

}
