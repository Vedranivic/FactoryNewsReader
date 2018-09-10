package hr.ferit.vedran.factorynewsreader.UI;

import android.util.Log;

import java.util.List;

import hr.ferit.vedran.factorynewsreader.Model.Article;
import hr.ferit.vedran.factorynewsreader.Model.Feed;
import hr.ferit.vedran.factorynewsreader.Networking.Client;
import hr.ferit.vedran.factorynewsreader.Networking.Constants;
import hr.ferit.vedran.factorynewsreader.Networking.NewsAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vedra on 9.9.2018..
 */

public class NewsPresenter implements NewsPresenterInterface {

    private NewsActivityInterface NAI;

    public NewsPresenter(NewsActivityInterface nai) {
        this.NAI = nai;
    }

    @Override
    public void getFeed(){
        Call<Feed> call = Client.getClient(Constants.BASE_URL).create(NewsAPI.class)
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
