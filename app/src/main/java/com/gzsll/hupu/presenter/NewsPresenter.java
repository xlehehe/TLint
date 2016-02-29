package com.gzsll.hupu.presenter;

import com.gzsll.hupu.support.storage.bean.News;
import com.gzsll.hupu.support.storage.bean.NewsResult;
import com.gzsll.hupu.view.NewsView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by sll on 2015/10/10.
 */
public class NewsPresenter extends Presenter<NewsView> {


@Inject
public NewsPresenter(){}

    private List<News> newsList = new ArrayList<>();
    private int type;
    private String nid;

    public void onNewsReceive(int type, String nid) {
        view.showLoading();
        this.type = type;
        this.nid = nid;
        loadNews(type, nid, true);
    }

    public void onRefresh() {
        view.onScrollToTop();
        nid = "0";
        loadNews(type, nid, true);
    }

    public void onLoadMore() {
        loadNews(type, nid, false);
    }


    private void loadNews(int type, String nid, final boolean isClear) {

    }

    private void loadFinish(NewsResult newsResult, boolean isClear) {
        if (isClear) {
            newsList.clear();
        }
        if (newsResult != null) {
            newsList.addAll(newsResult.getResult().getData());
            if (newsList.isEmpty()) {
                view.onEmpty();
            } else {
                nid = newsList.get(newsList.size() - 1).getNid();
                view.hideLoading();
                view.renderList(newsList);
            }
        } else {
            view.onError("数据加载失败");
        }
    }


    @Override
    public void initialize() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }
}
