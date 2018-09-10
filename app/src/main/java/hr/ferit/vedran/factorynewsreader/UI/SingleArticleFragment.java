package hr.ferit.vedran.factorynewsreader.UI;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.ferit.vedran.factorynewsreader.R;


/**
 * Created by vedra on 10.9.2018..
 */

public class SingleArticleFragment extends Fragment {

    @BindView(R.id.webview)
    WebView articleView;

    private String URL;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_single_article, container, false);
        ButterKnife.bind(this,rootView);
        setupUI();
        return rootView;
    }

    public void setupUI(){
        articleView.setWebViewClient(new MyBrowser());
        articleView.getSettings().setLoadsImagesAutomatically(true);
        articleView.getSettings().setJavaScriptEnabled(true);
        articleView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        articleView.loadUrl(getArguments().getString("URL"));
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

}
