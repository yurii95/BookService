package kozachok.jury.bookservice;

import android.util.Log;
import kozachok.jury.bookservice.data.BookResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BooksPresenter {
    private String LOG_TAG = MainActivity.class.getName();
    private IBooksView iBooksView;
    private int startIndex;
    private GoogleBooksService booksService;
    private String query;

    public BooksPresenter(IBooksView iBooksView, CharSequence query , int startIndex){
        this.iBooksView = iBooksView;
        this.startIndex = startIndex;
        this.query = query.toString();
        booksService = RetrofitClient.getRetrofitInstance().create(GoogleBooksService.class);

    }

    public void loadBooks(){
        iBooksView.onBooksLoading();
        Call<BookResponse> call = booksService.getBooks(query, startIndex);
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
                    iBooksView.showRefreshButton();
                }
            });
        }
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }
}
