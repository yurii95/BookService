package kozachok.jury.bookservice;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvItems = (RecyclerView) findViewById(R.id.rvBooks);
        rvItems.setHasFixedSize(true);
        layoutManager = new StaggeredGridLayoutManager(2,1);
        rvItems.setLayoutManager(layoutManager);

        scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                loadNextDataFromApi(5);
            }
        };
        rvItems.addOnScrollListener(scrollListener);
        mAdapter =new MyAdapter(booksList, this);
        rvItems.setAdapter(mAdapter);
        rvItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        booksPresenter = new BooksPresenter(this,this);

    }

    public void loadNextDataFromApi(int offset) {
        // Send an API request to retrieve appropriate paginated data
        //  --> Send the request including an offset value (i.e `page`) as a query parameter.
        //  --> Deserialize and construct new model objects from the API response
        //  --> Append the new data objects to the existing set of items inside the array of items
        //  --> Notify the adapter of the new items made with `notifyItemRangeInserted()`
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
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

    @Override
    public void onBooksLoaded(List<BookItem> books) {
        for (BookItem temp : books) {
            booksList.add(temp);
        }
        mAdapter.notifyDataSetChanged();
    }

}
