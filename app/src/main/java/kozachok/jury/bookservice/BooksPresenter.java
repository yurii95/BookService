package kozachok.jury.bookservice;

import android.content.Context;
import android.util.Log;
import kozachok.jury.bookservice.data.BookResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BooksPresenter {
    private String LOG_TAG = MainActivity.class.getName();
    private IBooksView iBooksView;
    private  Retrofit retrofit = RetrofitClient.getRetofitInstance();
    private  GoogleBooksService booksService;
    private  Call<BookResponse> call;
    private int startIndex;
    private String query;

    public BooksPresenter() {
    }

    public BooksPresenter(IBooksView iBooksView, String query , int startIndex){
        this.iBooksView = iBooksView;
        this.startIndex = startIndex;
        if(query!=null)
            this.query =query;

    }

    public void loadBooks(){
        booksService = retrofit.create(GoogleBooksService.class);
        call = booksService.getBooks(this.query, startIndex);
        if(!call.isExecuted()) {
            call.enqueue(new Callback<BookResponse>() {
                @Override
                public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {
                    if (response.isSuccessful()) {
                        Log.i(LOG_TAG, "response is "+response.message());
                        iBooksView.onBooksLoaded(response.body().getItems());
                    } else {
                        Log.i(LOG_TAG,"response is "+response.message());
                    }
                }
                @Override
                public void onFailure(Call<BookResponse> call, Throwable t) {
                    Log.i(LOG_TAG, "response is failure");
                }
            });
        }

    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }
}
