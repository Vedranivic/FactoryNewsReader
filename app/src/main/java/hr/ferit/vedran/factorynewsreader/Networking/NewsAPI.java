package hr.ferit.vedran.factorynewsreader.Networking;

import android.database.Observable;

import hr.ferit.vedran.factorynewsreader.Model.Feed;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by vedra on 9.9.2018..
 */

public interface NewsAPI {
    @GET("articles")
    Call<Feed> getArticles(@Query("source") String source,
                           @Query("sortBy") String sortBy,
                           @Query("apiKey") String apiKey
    );
}
