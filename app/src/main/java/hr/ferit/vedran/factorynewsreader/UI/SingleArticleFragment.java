package hr.ferit.vedran.factorynewsreader.UI;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
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
        try {
            articleView.loadUrl(getArguments().getString("URL"));
        }
        catch(Exception e){
            displayError(e.getMessage());
        }
    }

    public void displayError(String e) {
        Log.e("ERROR:", e);
        AlertDialog error = new AlertDialog.Builder(getActivity())
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


    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

}
