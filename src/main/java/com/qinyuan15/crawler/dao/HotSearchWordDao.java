package com.qinyuan15.crawler.dao;

import java.util.List;

/**
 * Dao of HotSearchWord
 * Created by qinyuan on 15-2-28.
 */
public class HotSearchWordDao {
    public Integer add(String content, Integer categoryId, Boolean hot) {
        HotSearchWord hotSearchWord = new HotSearchWord();
        hotSearchWord.setContent(content);
        hotSearchWord.setCategoryId(categoryId);
        hotSearchWord.setHot(hot);
        return new RankingDao().add(hotSearchWord);
    }

    public void update(Integer id, String content, Integer categoryId, Boolean hot) {
        HotSearchWord hotSearchWord = getInstance(id);
        hotSearchWord.setContent(content);
        hotSearchWord.setHot(hot);

        if (!hotSearchWord.getCategoryId().equals(categoryId)) {
            Integer maxRanking = new RankingDao().getMaxRanking(HotSearchWord.class);
            if (!maxRanking.equals(hotSearchWord.getRanking())) {
                hotSearchWord.setRanking(maxRanking + 1);
            }
            hotSearchWord.setCategoryId(categoryId);
        }

        HibernateUtil.update(hotSearchWord);
    }

    public HotSearchWord getInstance(Integer id) {
        return HibernateUtil.get(HotSearchWord.class, id);
    }

    public List<HotSearchWord> getInstances(Integer categoryId) {
        return HibernateUtil.getList(HotSearchWord.class, "categoryId=" + categoryId + " ORDER BY ranking ASC");
    }

    public void clear(int categoryId) {
        HibernateUtil.delete(HotSearchWord.class, "categoryId=" + categoryId);
    }

    public void rankUp(int id) {
        HotSearchWord current = getInstance(id);
        RankingDao rankingDao = new RankingDao();
        HotSearchWord previous = rankingDao.getPrevious(current, "categoryId=" + current.getCategoryId());
        rankingDao.switchRanking(current, previous);
    }

    public void rankDown(int id) {
        HotSearchWord current = getInstance(id);
        RankingDao rankingDao = new RankingDao();
        HotSearchWord next = rankingDao.getNext(current, "categoryId=" + current.getCategoryId());
        rankingDao.switchRanking(current, next);
    }
}
