package hr.ferit.vedran.factorynewsreader.UI;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.ferit.vedran.factorynewsreader.Adapter.FeedAdapter;
import hr.ferit.vedran.factorynewsreader.Model.Article;
import hr.ferit.vedran.factorynewsreader.Model.Feed;
import hr.ferit.vedran.factorynewsreader.R;
import okhttp3.Cache;

public class NewsActivity extends AppCompatActivity implements NewsActivityInterface {

    @BindView(R.id.rvFeed)
    RecyclerView rvFeed;

    RecyclerView.Adapter adapter;
    NewsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);

        setupMVP();
        setupViews();
        getNewsFeed();
    }

    private void setupMVP() {
        presenter = new NewsPresenter(this, this.getApplicationContext());
    }

    private void setupViews(){
        rvFeed.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getNewsFeed() {
        presenter.getFeed();
    }



    @Override
    public void displayArticles(Feed newsfeed, FeedAdapter.ArticleClickCallback clickCallback) {
        if(newsfeed!=null) {
            adapter = new FeedAdapter(newsfeed.getArticles(), NewsActivity.this, clickCallback);
            rvFeed.setAdapter(adapter);
        }
        else{
            Log.d("NewsActivity error","Movies response null");
        }
    }

    @Override
    public void displayError(String e) {
        Log.e("ERROR:", e);
        AlertDialog error = new AlertDialog.Builder(this)
                .setTitle("Greška")
                .setMessage("Ups. Došlo je do pogreške")
                .setNegativeButton("U redu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        error.show();
    }
}
