package json.chao.com.wanandroid.ui.mainpager.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import json.chao.com.wanandroid.R;
import json.chao.com.wanandroid.core.bean.main.collect.FeedArticleData;
import json.chao.com.wanandroid.ui.mainpager.viewholder.KnowledgeHierarchyListViewHolder;

/**
 * @author quchao
 * @date 2018/2/24
 */

public class ArticleListAdapter extends BaseQuickAdapter<FeedArticleData, KnowledgeHierarchyListViewHolder> {

    private boolean isCollectPage;
    private boolean isSearchPage;

    public ArticleListAdapter(int layoutResId, @Nullable List<FeedArticleData> data) {
        super(layoutResId, data);
    }

    public void isCollectPage() {
        isCollectPage = true;
        notifyDataSetChanged();
    }

    public void isSearchPage() {
        isSearchPage = true;
        notifyDataSetChanged();
    }

    @Override
    protected void convert(KnowledgeHierarchyListViewHolder helper, FeedArticleData article) {
        if (!TextUtils.isEmpty(article.getTitle())) {
            helper.setText(R.id.item_search_pager_title, Html.fromHtml(article.getTitle()));
        }
        if (article.isCollect() || isCollectPage) {
            helper.setImageResource(R.id.item_search_pager_like_iv, R.drawable.icon_like_article_selected);
        } else {
            helper.setImageResource(R.id.item_search_pager_like_iv, R.drawable.icon_like_article_not_selected);
        }
        if (!TextUtils.isEmpty(article.getAuthor())) {
            helper.setText(R.id.item_search_pager_author, article.getAuthor());
        }
        setTag(helper, article);
        if (!TextUtils.isEmpty(article.getChapterName())) {
            String classifyName = article.getSuperChapterName() + " / " + article.getChapterName();
            if (isCollectPage) {
                helper.setText(R.id.item_search_pager_chapterName, article.getChapterName());
            } else {
                helper.setText(R.id.item_search_pager_chapterName, classifyName);
            }
        }
        if (!TextUtils.isEmpty(article.getNiceDate())) {
            helper.setText(R.id.item_search_pager_niceDate, article.getNiceDate());
        }
        if (isSearchPage) {
            CardView cardView = helper.getView(R.id.item_search_pager_group);
            cardView.setForeground(null);
            cardView.setBackground(ContextCompat.getDrawable(mContext, R.drawable.selector_search_item_bac));
        }

        helper.addOnClickListener(R.id.item_search_pager_chapterName);
        helper.addOnClickListener(R.id.item_search_pager_like_iv);
        helper.addOnClickListener(R.id.item_search_pager_tag_tv);
    }

    private void setTag(KnowledgeHierarchyListViewHolder helper, FeedArticleData article) {
        helper.getView(R.id.item_search_pager_tag_tv).setVisibility(View.GONE);
        if (isCollectPage) {
            return;
        }
        if (article.getSuperChapterName().contains(mContext.getString(R.string.open_project))) {
            helper.getView(R.id.item_search_pager_tag_tv).setVisibility(View.VISIBLE);
            helper.setText(R.id.item_search_pager_tag_tv, R.string.project);
            helper.setTextColor(R.id.item_search_pager_tag_tv, ContextCompat.getColor(mContext, R.color.light_deep_red));
            helper.setBackgroundRes(R.id.item_search_pager_tag_tv, R.drawable.selector_tag_red_background);
        }

        if (article.getSuperChapterName().contains(mContext.getString(R.string.navigation))) {
            helper.getView(R.id.item_search_pager_tag_tv).setVisibility(View.VISIBLE);
            helper.setText(R.id.item_search_pager_tag_tv, R.string.navigation);
            helper.setTextColor(R.id.item_search_pager_tag_tv, ContextCompat.getColor(mContext, R.color.light_deep_red));
            helper.setBackgroundRes(R.id.item_search_pager_tag_tv, R.drawable.selector_tag_red_background);
        }

        if (article.getNiceDate().contains(mContext.getString(R.string.minute))
                || article.getNiceDate().contains(mContext.getString(R.string.hour))
                || article.getNiceDate().contains(mContext.getString(R.string.one_day))) {
            helper.getView(R.id.item_search_pager_tag_tv).setVisibility(View.VISIBLE);
            helper.setText(R.id.item_search_pager_tag_tv, R.string.text_new);
            helper.setTextColor(R.id.item_search_pager_tag_tv, ContextCompat.getColor(mContext, R.color.light_green));
            helper.setBackgroundRes(R.id.item_search_pager_tag_tv, R.drawable.shape_tag_green_background);
        }


    }
}
