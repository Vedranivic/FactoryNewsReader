package hr.ferit.vedran.factorynewsreader.Model;

import java.util.List;

/**
 * Created by vedra on 9.9.2018..
 */

public class Feed {

    private String status;
    private String source;
    private String sortBy;
    private List<Article> articles;

    public Feed() {
    }

    public Feed(String status, String source, String sortBy, List<Article> articles) {
        this.status = status;
        this.source = source;
        this.sortBy = sortBy;
        this.articles = articles;
    }

    public List<Article> getArticles ()
    {
        return articles;
    }

    public void setArticles (List<Article> articles)
    {
        this.articles = articles;
    }

    public String getSortBy ()
    {
        return sortBy;
    }

    public void setSortBy (String sortBy)
    {
        this.sortBy = sortBy;
    }

    public String getSource ()
    {
        return source;
    }

    public void setSource (String source)
    {
        this.source = source;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [articles = "+articles+", sortBy = "+sortBy+", source = "+source+", status = "+status+"]";
    }
}
