package hr.ferit.vedran.factorynewsreader.UI;

import hr.ferit.vedran.factorynewsreader.Model.Feed;

/**
 * Created by vedra on 9.9.2018..
 */

public interface NewsActivityInterface {
    void showToast(String s);
    void displayArticles(Feed newsfeed);
    void displayError(String s);
}
