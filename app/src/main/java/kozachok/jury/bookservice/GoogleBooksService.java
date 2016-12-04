package kozachok.jury.bookservice;

import kozachok.jury.bookservice.data.BookResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Юрий on 02.12.2016.
 */
public interface GoogleBooksService {
    @GET("volumes?q=Gaiman%20Niel&startIndex=0&key=AIzaSyDYxnciDQ6KUwEJCJgynVDpQTu0O3ahNfQ")
    Call<BookResponse> getBooks();
}
