package kozachok.jury.bookservice;

import android.app.SearchManager;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

import kozachok.jury.bookservice.data.BookItem;


public class MainActivity extends AppCompatActivity implements IBooksView{
    private String LOG_TAG = MainActivity.class.getName();
    private RecyclerView rvItems;
    private EndlessRecyclerViewScrollListener scrollListener;
    private List<BookItem> booksList = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private BooksPresenter booksPresenter;
    private SearchView searchView;
    private int offset;
    private CharSequence query;
    private ProgressBar download_progress;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        query = "";
        download_progress = (ProgressBar)findViewById(R.id.download_progress);

        Button btnRefresh = (Button) findViewById(R.id.button);
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(LOG_TAG, " reconnect");
                download_progress.setVisibility(View.VISIBLE);
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)rvItems.getLayoutParams();
                params.weight = 0;
                rvItems.setLayoutParams(params);
                booksPresenter.loadBooks();
//                loadNextDataFromApi(offset);
            }
        });

        if(searchView != null) {
            query = searchView.getQuery();
        }
        if (savedInstanceState != null) {
            Log.i(LOG_TAG, " restitution");
            booksList = savedInstanceState.getParcelableArrayList("book_list");
            offset = savedInstanceState.getInt("offset");
            query = savedInstanceState.getCharSequence("query");
            booksPresenter = new BooksPresenter(this, query, offset);
//            booksPresenter.loadBookvs();
        }

        StaggeredGridLayoutManager layoutManager;
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            layoutManager = new StaggeredGridLayoutManager(3, 1);
        else
            layoutManager = new StaggeredGridLayoutManager(2,1);

        rvItems = (RecyclerView) findViewById(R.id.rvBooks);
        rvItems.setHasFixedSize(true);
        rvItems.setLayoutManager(layoutManager);
        scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                offset = totalItemsCount;
                booksPresenter.setStartIndex(offset);
                booksPresenter.loadBooks();
            }
        };
        rvItems.addOnScrollListener(scrollListener);
        mAdapter =new MyAdapter(booksList, this);
        rvItems.setAdapter(mAdapter);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.i(LOG_TAG, " saving state");
        outState.putInt("offset", offset);
        outState.putCharSequence("query", query);
        outState.putParcelableArrayList("book_list",(ArrayList<? extends Parcelable>)booksList);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        final SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.i(LOG_TAG, " submit query");
                download_progress.setVisibility(View.VISIBLE);
                scrollListener.reset();
                booksList.clear();
                mAdapter.notifyDataSetChanged();
                offset = 0;
                MainActivity.this.query = query;
                booksPresenter = new BooksPresenter(MainActivity.this, query, offset);
                booksPresenter.loadBooks();
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    // set focus immediately after click search button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.search:
                searchView.setQuery(query,false);
                searchView.setIconified(false);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    // add new data to list
    @Override
    public void onBooksLoaded(List<BookItem> books) {
        download_progress.setVisibility(View.GONE);
        if (books != null && books.size() != 0) {
            for (BookItem temp : books) {
                if (temp != null && temp.getVolumeInfo() != null &&
                        temp.getVolumeInfo().getImageLinks() != null &&
                        temp.getVolumeInfo().getImageLinks().getThumbnail() != null &&
                        temp.getVolumeInfo().getTitle() != null &&
                        !booksList.contains(temp)) {
                    booksList.add(temp);
                }
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void showRefreshButton() {
        download_progress.setVisibility(View.GONE);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)rvItems.getLayoutParams();
        params.weight = 0.9f;
        rvItems.setLayoutParams(params);
    }


    @Override
    public void onBooksLoading() {
        download_progress.setVisibility(View.VISIBLE);
    }
}
