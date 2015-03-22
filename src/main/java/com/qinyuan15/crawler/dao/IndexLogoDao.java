package com.qinyuan15.crawler.dao;

import java.util.List;

/**
 * Dao of IndexLogo
 * Created by qinyuan on 15-3-19.
 */
public class IndexLogoDao {
    public List<IndexLogo> getInstances() {
        return HibernateUtils.getList(IndexLogo.class, RankingDao.ASC_ORDER);
    }

    public void delete(Integer id) {
        HibernateUtils.delete(IndexLogo.class, id);
    }

    public Integer add(String path, String link, String description) {
        IndexLogo indexLogo = new IndexLogo();
        indexLogo.setPath(path);
        indexLogo.setLink(link);
        indexLogo.setDescription(description);
        return new RankingDao().add(indexLogo);
    }

    public IndexLogo getInstance(int id) {
        return HibernateUtils.get(IndexLogo.class, id);
    }

    public void rankUp(int id) {
        IndexLogo current = getInstance(id);
        RankingDao rankingDao = new RankingDao();
        IndexLogo previous = rankingDao.getPrevious(current);
        rankingDao.switchRanking(current, previous);
    }

    public void rankDown(int id) {
        IndexLogo current = getInstance(id);
        RankingDao rankingDao = new RankingDao();
        IndexLogo next = rankingDao.getNext(current);
        rankingDao.switchRanking(current, next);
    }
}
