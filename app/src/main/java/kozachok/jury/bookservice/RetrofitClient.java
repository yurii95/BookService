package kozachok.jury.bookservice;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Юрий on 04.12.2016.
 */
public class RetrofitClient {
    private static Retrofit retrofit;
    private static String BASE_URL = "https://www.googleapis.com/books/v1/volumes/";

    public RetrofitClient() {
    }
    public static Retrofit getRetofitInstance(){
        if (retrofit == null)
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit;
    }
}
