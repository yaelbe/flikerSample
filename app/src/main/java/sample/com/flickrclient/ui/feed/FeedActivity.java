package sample.com.flickrclient.ui.feed;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import sample.com.flickrclient.R;
import sample.com.flickrclient.databinding.ActivityFeedBinding;
import sample.com.flickrclient.ui.detail.ImageDetailActivity;

public class FeedActivity extends DaggerAppCompatActivity {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    ActivityFeedBinding binding;

    @Inject
    FeedAdapter feedAdapter;

    FeedViewModel feedViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        feedViewModel = ViewModelProviders.of(this, viewModelFactory).get(FeedViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_feed);
        initUI();
        observe();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        SearchView search = (SearchView) menu.findItem(R.id.action_search).getActionView();
        search.setActivated(true);
        search.setQueryHint("Type your keyword here");
        search.onActionViewExpanded();
        search.setIconified(false);
        search.clearFocus();
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                feedViewModel.setFilter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.isEmpty()){
                    feedViewModel.setFilter("");
                }
                return false;
            }
        });

        return true;
    }

    private void initUI() {
        binding.infiniteRecyclerView.setAdapter(feedAdapter);
        binding.infiniteRecyclerView.setPageListener(() -> feedViewModel.loadNextPage());
        feedAdapter.setOnItemClickListener(photoInfo -> startActivity(ImageDetailActivity.newIntent(this, photoInfo)));
    }

    private void observe() {
        feedViewModel.getPhotos().observe(this, feedAdapter::setPhotos);
        feedViewModel.getIsLoading().observe(this, loading -> {
            binding.refreshLayout.setRefreshing(loading);
            binding.infiniteRecyclerView.setLoading(loading);
        });
    }
}
