package kozachok.jury.bookservice;

import android.util.Log;

import java.util.List;

import kozachok.jury.bookservice.data.BookItem;
import kozachok.jury.bookservice.data.BookResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Юрий on 05.12.2016.
 */
public class RestClient {
    private static Retrofit retrofit = RetrofitClient.getRetofitInstance();
    private static GoogleBooksService booksService = retrofit.create(GoogleBooksService.class);
    private static Call<BookResponse> call = booksService.getBooks();
    private static List<BookItem> books;

    public static  List<BookItem> getAllBooks() {

        call.enqueue(new Callback<BookResponse>() {
            @Override
            public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {
                String str = "";
                if (response.isSuccessful()) {
                    Log.d("response", response.message());
                    Log.d("response", String.valueOf(response.code()));
                    BookResponse volumeInfo = response.body();
                    books = volumeInfo.getItems();
                    for (BookItem temp : books) {
                        System.out.println(temp.getVolumeInfo().getTitle());
                        System.out.println(temp.getVolumeInfo().getPreviewLink());
                    }
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
        return books;
    }


}
