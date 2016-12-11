package kozachok.jury.bookservice;

import kozachok.jury.bookservice.data.BookResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GoogleBooksService {
    @GET("?&key=AIzaSyDYxnciDQ6KUwEJCJgynVDpQTu0O3ahNfQ&maxResults=25")
    Call<BookResponse> getBooks(@Query("q") String author, @Query("startIndex") int index);
}
