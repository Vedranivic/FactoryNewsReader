package hr.ferit.vedran.factorynewsreader.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.ferit.vedran.factorynewsreader.Adapter.FeedAdapter;
import hr.ferit.vedran.factorynewsreader.Model.Feed;
import hr.ferit.vedran.factorynewsreader.R;

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
        presenter = new NewsPresenter(this);
    }

    private void setupViews(){
        rvFeed.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getNewsFeed() {
        presenter.getFeed();
    }

    @Override
    public void showToast(String str) {
        Toast.makeText(NewsActivity.this,str,Toast.LENGTH_LONG).show();
    }

    @Override
    public void displayArticles(Feed newsfeed) {
        if(newsfeed!=null) {
            Log.d("NewsActivity success",newsfeed.getArticles().get(1).getTitle());
            adapter = new FeedAdapter(newsfeed.getArticles(), NewsActivity.this);
            rvFeed.setAdapter(adapter);
        }else{
            Log.d("NewsActivity error","Movies response null");
        }
    }

    @Override
    public void displayError(String e) {
        showToast(e);
    }
}
