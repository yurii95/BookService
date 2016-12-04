package kozachok.jury.bookservice;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.SearchView;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    String BASE_URL = "https://www.googleapis.com/books/v1/";
    String LOG_TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit client = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        GoogleBooksService booksService = client.create(GoogleBooksService.class);
        Call<VolumeInfo> call = booksService.getBooks();

        call.enqueue(new Callback<VolumeInfo>() {
            @Override
            public void onResponse(Call<VolumeInfo> call, Response<VolumeInfo> response) {
                String str="";
                if (response.isSuccessful()) {
                    Log.d("response","successful");
                    VolumeInfo volumeInfo = response.body();
                    Log.d(LOG_TAG, String.valueOf(volumeInfo));
                    Log.d(LOG_TAG,volumeInfo.getTitle());
                } else {
                    Log.d("response","not successful");
                }
            }

            @Override
            public void onFailure(Call<VolumeInfo> call, Throwable t) {
            }
        });
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
