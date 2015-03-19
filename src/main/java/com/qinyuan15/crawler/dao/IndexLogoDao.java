package com.qinyuan15.crawler.dao;

import com.qinyuan15.crawler.core.IntegerUtils;

import java.util.List;

/**
 * Dao of IndexLogo
 * Created by qinyuan on 15-3-19.
 */
public class IndexLogoDao {
    private final static String ORDER_CLAUSE = " ORDER BY ranking DESC";

    public List<IndexLogo> getInstances() {
        return HibernateUtil.getList(IndexLogo.class, ORDER_CLAUSE);
    }

    public Integer add(String path, String link) {
        IndexLogo indexLogo = new IndexLogo();
        indexLogo.setPath(path);
        indexLogo.setLink(link);

        Integer maxRanking = getMaxRanking();
        if (IntegerUtils.isPositive(maxRanking)) {
            indexLogo.setRanking(maxRanking + 1);
        } else {
            indexLogo.setRanking(1);
        }
        return HibernateUtil.save(indexLogo);
    }

    public IndexLogo getInstance(int id) {
        return HibernateUtil.get(IndexLogo.class, id);
    }

    public void rankUp(int id) {
        IndexLogo current = getInstance(id);
        if (current == null) {
            return;
        }

        IndexLogo previous = getPrevious(current);
        if (previous == null) {
            return;
        }

        switchRanking(current, previous);
    }

    public void rankDown(int id) {
        IndexLogo current = getInstance(id);
        if (current == null) {
            return;
        }

        IndexLogo next = getNext(current);
        if (next == null) {
            return;
        }

        switchRanking(current, next);
    }

    private void switchRanking(IndexLogo indexLogo1, IndexLogo indexLogo2) {
        Integer ranking1 = indexLogo1.getRanking();
        Integer ranking2 = indexLogo2.getRanking();

        indexLogo2.setRanking(-1 * ranking2);
        HibernateUtil.update(indexLogo2);

        indexLogo1.setRanking(ranking2);
        HibernateUtil.update(indexLogo1);

        indexLogo2.setRanking(ranking1);
        HibernateUtil.update(indexLogo2);
    }

    public IndexLogo getNext(IndexLogo indexLogo) {
        if (indexLogo == null) {
            return null;
        }
        return getInstance("ranking>" + indexLogo.getRanking());
    }

    private IndexLogo getInstance(String whereClause) {
        String hql = "FROM IndexLogo WHERE " + whereClause + ORDER_CLAUSE;
        @SuppressWarnings("unchecked")
        List<IndexLogo> previousInstances = HibernateUtil.getList(hql, 0, 1);
        return previousInstances.size() == 0 ? null : previousInstances.get(0);
    }

    public IndexLogo getPrevious(IndexLogo indexLogo) {
        if (indexLogo == null) {
            return null;
        }
        return getInstance("ranking<" + indexLogo.getRanking());
    }

    public Integer getMaxRanking() {
        return getIntValue("SELECT MAX(ranking) FROM IndexLogo");
    }

    private Integer getIntValue(String hql) {
        return (Integer) HibernateUtil.getList(hql).get(0);
    }
}
