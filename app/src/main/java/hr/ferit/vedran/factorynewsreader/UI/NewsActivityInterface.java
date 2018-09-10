package hr.ferit.vedran.factorynewsreader.UI;

import hr.ferit.vedran.factorynewsreader.Adapter.FeedAdapter;
import hr.ferit.vedran.factorynewsreader.Model.Feed;

/**
 * Created by vedra on 9.9.2018..
 */

public interface NewsActivityInterface {
    void displayArticles(Feed newsfeed, FeedAdapter.ArticleClickCallback clickCallback);
    void displayError(String s);
}
