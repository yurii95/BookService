package kozachok.jury.bookservice;

import android.app.SearchManager;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.widget.SwipeRefreshLayout;
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
    private StaggeredGridLayoutManager layoutManager;
    private EndlessRecyclerViewScrollListener scrollListener;
    private List<BookItem> booksList = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private BooksPresenter booksPresenter;
    private SearchView searchView;
    private int offset;
    private CharSequence query = "";
    private Button btnRefresh;
    private ProgressBar download_progress;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnRefresh = (Button) findViewById(R.id.button);
        download_progress = (ProgressBar)findViewById(R.id.download_progress);

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download_progress.setVisibility(View.VISIBLE);
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)rvItems.getLayoutParams();
                params.weight = 0;
                rvItems.setLayoutParams(params);
                loadNextDataFromApi(offset);
            }
        });

        if(searchView != null) {
            query = searchView.getQuery();
        }
        if (savedInstanceState != null) {
            booksList = savedInstanceState.getParcelableArrayList("book_list");
            offset = savedInstanceState.getInt("offset");
            query = savedInstanceState.getCharSequence("query");
            booksPresenter = new BooksPresenter(this, query.toString(), offset);
            booksPresenter.loadBooks();
        }
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
                loadNextDataFromApi(totalItemsCount);
            }
        };
        rvItems.addOnScrollListener(scrollListener);
        mAdapter =new MyAdapter(booksList, this);
        rvItems.setAdapter(mAdapter);


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("offset", offset);
        outState.putCharSequence("query", query);
        outState.putParcelableArrayList("book_list",(ArrayList<? extends Parcelable>)booksList);
        super.onSaveInstanceState(outState);
    }

    public void loadNextDataFromApi(int offset) {
        this.offset = offset;
        booksPresenter.setStartIndex(offset);
        booksPresenter.loadBooks();
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
//                for (BookItem temp : booksList) {
//                    System.out.println(temp.getVolumeInfo().toString());;
//                }
                download_progress.setVisibility(View.VISIBLE);
                scrollListener.reset();
                booksList.clear();
                mAdapter.notifyDataSetChanged();
                offset = 0;
                sendQuery(query);
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

    public void sendQuery(String query){
        System.out.println("ушло offset = "+ offset);
        this.query =query;
        booksPresenter = new BooksPresenter(this, query, offset);
    }

    @Override
    public void onBooksLoaded(List<BookItem> books) {
        download_progress.setVisibility(View.GONE);
        if (books != null) {
            if (books.size() != 0) {
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
    }

    @Override
    public void showRefreshButton() {
        download_progress.setVisibility(View.GONE);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)rvItems.getLayoutParams();
        params.weight = 0.9f;
        rvItems.setLayoutParams(params);
    }
}
