package hr.ferit.vedran.factorynewsreader.UI;

import hr.ferit.vedran.factorynewsreader.Model.Feed;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by vedra on 9.9.2018..
 */

public interface NewsPresenterInterface extends Callback<Feed> {
    void getFeed();
}
