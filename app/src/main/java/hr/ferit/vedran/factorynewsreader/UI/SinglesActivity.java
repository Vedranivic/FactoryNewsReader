package hr.ferit.vedran.factorynewsreader.UI;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.ferit.vedran.factorynewsreader.Adapter.SinglesPagerAdapter;
import hr.ferit.vedran.factorynewsreader.R;

/**
 * Created by vedra on 10.9.2018..
 */

public class SinglesActivity extends FragmentActivity {

    @BindView(R.id.pager)
    ViewPager vpSingles;

    @BindView(R.id.pbLoad)
    ProgressBar pbLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singles);
        ButterKnife.bind(this);

        setupUI();
    }

    private void setupUI(){
        pbLoad.setVisibility(View.VISIBLE);

        String[] articleURLs = getIntent().getExtras().getStringArray("ARTICLE_URLS");
        int position = getIntent().getIntExtra("position", 0);

        SinglesPagerAdapter mPagerAdapter = new SinglesPagerAdapter(getSupportFragmentManager(), articleURLs);
        vpSingles.setAdapter(mPagerAdapter);
        vpSingles.setCurrentItem(position);
    }

}
