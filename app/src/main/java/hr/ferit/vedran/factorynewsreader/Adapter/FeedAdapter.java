package hr.ferit.vedran.factorynewsreader.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.ferit.vedran.factorynewsreader.Model.Article;
import hr.ferit.vedran.factorynewsreader.R;

/**
 * Created by vedra on 10.9.2018..
 */

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedViewHolder> {
    private List<Article> newsFeed;
    private Context context;
    private ArticleClickCallback clickCallback;

    public FeedAdapter(List<Article> newsFeed, Context context, ArticleClickCallback clickCallback) {
        this.newsFeed = newsFeed;
        this.context = context;
        this.clickCallback = clickCallback;
    }

    @Override
    public FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.news_item,parent,false);
        FeedViewHolder holder = new FeedViewHolder(v,clickCallback);
        return holder;
    }

    @Override
    public void onBindViewHolder(FeedAdapter.FeedViewHolder holder, int position) {
        Article article = this.newsFeed.get(position);
        holder.tvTitle.setText(article.getTitle());
        holder.tvDescription.setText(article.getDescription());
        Picasso.get()
                .load(article.getUrlToImage())
                .fit()
                .centerCrop()
                .into(holder.ivImage);
    }

    @Override
    public int getItemCount() {
        if(newsFeed!=null)
            return this.newsFeed.size();
        else
            return 0;
    }

    public class FeedViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        TextView tvTitle;
        @BindView(R.id.description)
        TextView tvDescription;
        @BindView(R.id.image)
        ImageView ivImage;

        public FeedViewHolder(View article, final ArticleClickCallback clickCallback) {
            super(article);
            ButterKnife.bind(this,article);
            article.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickCallback.onClick(newsFeed.get(getAdapterPosition()), getAdapterPosition());
                }
            });
        }
    }

    public interface ArticleClickCallback {
        void onClick(Article article, int position);
    }

}
