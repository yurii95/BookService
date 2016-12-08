package kozachok.jury.bookservice;

import android.content.Context;
import android.util.Log;
import kozachok.jury.bookservice.data.BookResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BooksPresenter {
    private IBooksView iBooksView;
    private  Retrofit retrofit = RetrofitClient.getRetofitInstance();
    private  GoogleBooksService booksService;
    private  Call<BookResponse> call;
    private int startIndex;
    private String query;

    public BooksPresenter(IBooksView iBooksView, String query , int startIndex){
        this.iBooksView = iBooksView;
        this.startIndex = startIndex;
        if(query!=null)
            this.query =query;
        booksService = retrofit.create(GoogleBooksService.class);
        call = booksService.getBooks(this.query, startIndex);
    }

    public void loadBooks(){
        call.enqueue(new Callback<BookResponse>() {
            @Override
            public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("response", response.message());
                    Log.d("response", String.valueOf(response.code()));
                    BookResponse volumeInfo = response.body();
                    iBooksView.onBooksLoaded(volumeInfo.getItems());
                } else {

                    Log.d("response", response.message());
                    Log.d("response", String.valueOf(response.code()));
                    Log.d("response", "not successful");
                }
            }

            @Override
            public void onFailure(Call<BookResponse> call, Throwable t) {
            }
        });

    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }
}
