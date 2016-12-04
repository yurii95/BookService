package kozachok.jury.bookservice;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Юрий on 02.12.2016.
 */
public interface GoogleBooksService {
    @GET("volumes?q=Gaiman%20Niel&startIndex=0")
    Call<VolumeInfo> getBooks();
}