package kozachok.jury.bookservice;

import android.content.Context;
import android.util.Log;
import kozachok.jury.bookservice.data.BookResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BooksPresenter {

    private Context context;
    private IBooksView iBooksView;
    private  Retrofit retrofit = RetrofitClient.getRetofitInstance();
    private  GoogleBooksService booksService = retrofit.create(GoogleBooksService.class);
    private  Call<BookResponse> call = booksService.getBooks();

    public BooksPresenter(Context context, IBooksView iBooksView){
        this.context = context;
        this.iBooksView = iBooksView;
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
}
