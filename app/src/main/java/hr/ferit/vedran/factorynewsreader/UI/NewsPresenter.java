package hr.ferit.vedran.factorynewsreader.UI;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.util.List;

import hr.ferit.vedran.factorynewsreader.Model.Article;
import hr.ferit.vedran.factorynewsreader.Model.Feed;
import hr.ferit.vedran.factorynewsreader.Networking.Client;
import hr.ferit.vedran.factorynewsreader.Networking.Constants;
import hr.ferit.vedran.factorynewsreader.Networking.NewsAPI;
import okhttp3.Cache;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vedra on 9.9.2018..
 */

public class NewsPresenter implements NewsPresenterInterface {

    private NewsActivityInterface NAI;
    private Context context;

    public NewsPresenter(NewsActivityInterface nai, Context context) {
        this.NAI = nai;
        this.context = context;
    }

    @Override
    public void getFeed(){
        Call<Feed> call = Client.getClient(Constants.BASE_URL, context).create(NewsAPI.class)
                .getArticles("bbc-news","top",Constants.API_KEY);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Feed> call, Response<Feed> response) {
        if(response.body() != null){
            Feed newsfeed = response.body();
            NAI.displayArticles(newsfeed);
        }
    }

    @Override
    public void onFailure(Call<Feed> call, Throwable t) {
        NAI.displayError(t.getMessage());
    }
}
