package hr.ferit.vedran.factorynewsreader.Adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import hr.ferit.vedran.factorynewsreader.UI.SingleArticleFragment;
import hr.ferit.vedran.factorynewsreader.UI.SinglesActivity;

/**
 * Created by vedra on 10.9.2018..
 */

public class SinglesPagerAdapter extends FragmentStatePagerAdapter{

    private static final int NUM_PAGES = 10;
    private String[] articleURLs;

    public SinglesPagerAdapter(FragmentManager fm, String[] articleURLs) {
        super(fm);
        this.articleURLs = articleURLs;
    }

    @Override
    public Fragment getItem(int position) {
        SingleArticleFragment article = new SingleArticleFragment();
        Bundle bundle = new Bundle();
        bundle.putString("URL",articleURLs[position]);
        article.setArguments(bundle);
        return article;
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }

}
