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
import android.widget.SearchView;

import java.util.List;

import kozachok.jury.bookservice.data.BookItem;
import kozachok.jury.bookservice.data.BookResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity {
    private Retrofit retrofit = RetrofitClient.getRetofitInstance();
    private String LOG_TAG = MainActivity.class.getName();
    private RecyclerView rvItems;
    private StaggeredGridLayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GoogleBooksService booksService = retrofit.create(GoogleBooksService.class);
        Call<BookResponse> call = booksService.getBooks();

        call.enqueue(new Callback<BookResponse>() {
            @Override
            public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {
                String str="";
                if (response.isSuccessful()) {
                    Log.d("response",response.message());
                    Log.d("response",String.valueOf(response.code()));
                    BookResponse volumeInfo = response.body();
                    Log.d(LOG_TAG, String.valueOf(volumeInfo));
                    List<BookItem> list = volumeInfo.getItems();
                    for(BookItem temp : list){
                        System.out.println(temp.getVolumeInfo().getTitle());
                        System.out.println(temp.getVolumeInfo().getPreviewLink());
                    }
                } else {

                    Log.d("response",response.message());
                    Log.d("response",String.valueOf(response.code()));
                    Log.d("response","not successful");
                }
            }

            @Override
            public void onFailure(Call<BookResponse> call, Throwable t) {
                Log.d(LOG_TAG,"OnFailure");
            }
        });

        rvItems = (RecyclerView) findViewById(R.id.rvBooks);
        rvItems.setHasFixedSize(true);
        layoutManager = new StaggeredGridLayoutManager(2,1);
        rvItems.setLayoutManager(layoutManager);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }
}
