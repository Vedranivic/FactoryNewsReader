package hr.ferit.vedran.factorynewsreader.UI;

import android.content.Context;
import android.content.Intent;
import android.transition.ArcMotion;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import hr.ferit.vedran.factorynewsreader.Adapter.FeedAdapter;
import hr.ferit.vedran.factorynewsreader.Model.Article;
import hr.ferit.vedran.factorynewsreader.Model.Feed;
import hr.ferit.vedran.factorynewsreader.Networking.Client;
import hr.ferit.vedran.factorynewsreader.Networking.Constants;
import hr.ferit.vedran.factorynewsreader.Networking.NewsAPI;
import okhttp3.Cache;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.System.in;

/**
 * Created by vedra on 9.9.2018..
 */

public class NewsPresenter implements NewsPresenterInterface {

    private NewsActivityInterface NAI;
    private Context context;
    private Feed newsFeed;


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
            newsFeed = response.body();
            NAI.displayArticles(newsFeed, clickCallback);
        }
    }

    @Override
    public void onFailure(Call<Feed> call, Throwable t) {
        NAI.displayError(t.getMessage());
    }

    private FeedAdapter.ArticleClickCallback clickCallback = new FeedAdapter.ArticleClickCallback() {
        @Override
        public void onClick(Article article, int position) {
            Intent intent = new Intent(context, SinglesActivity.class);
            List<String> listURL = new ArrayList<String>();
            if(newsFeed!=null) {
                for(Article a : newsFeed.getArticles()){
                    listURL.add(a.getUrl());
                }
                String[] articleURLs = listURL.toArray(new String[0]);
                intent.putExtra("ARTICLE_URLS", articleURLs);
                intent.putExtra("position", position);
                context.startActivity(intent);
                Log.e("POSITION:::::::::::", String.valueOf(position));
            }
        }
    };
}
