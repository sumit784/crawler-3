package com.qinyuan15.crawler.dao;

import com.qinyuan15.crawler.core.IntegerUtils;
import org.springframework.util.StringUtils;

/**
 * Dao about Ranking Persist Object
 * Created by qinyuan on 15-3-21.
 */
public class RankingDao {

    public final static String ASC_ORDER = " ORDER BY ranking ASC";
    public final static String DESC_ORDER = " ORDER BY ranking DESC";

    public Integer getMaxRanking(Class<? extends Ranking> clazz) {
        return (Integer) HibernateUtil.getFirstItem("SELECT MAX(ranking) FROM " + clazz.getSimpleName());
    }

    public void switchRanking(Ranking ranking1, Ranking ranking2) {
        if (ranking1 == null || ranking2 == null) {
            return;
        }

        Integer rankingValue1 = ranking1.getRanking();
        Integer rankingValue2 = ranking2.getRanking();

        ranking2.setRanking(-1 * rankingValue2);
        HibernateUtil.update(ranking2);

        ranking1.setRanking(rankingValue2);
        HibernateUtil.update(ranking1);

        ranking2.setRanking(rankingValue1);
        HibernateUtil.update(ranking2);
    }

    public <T extends Ranking> T getPrevious(T ranking) {
        return getPrevious(ranking, null);
    }

    @SuppressWarnings("unchecked")
    public <T extends Ranking> T getPrevious(T ranking, String whereClause) {
        if (ranking == null) {
            return null;
        }
        String hql = "FROM " + ranking.getClass().getSimpleName() +
                " WHERE ranking<" + ranking.getRanking();
        if (StringUtils.hasText(whereClause)) {
            hql += " AND " + whereClause;
        }
        hql += DESC_ORDER;
        return (T) HibernateUtil.getFirstItem(hql);
    }

    public <T extends Ranking> T getNext(T ranking) {
        return getNext(ranking, null);
    }

    @SuppressWarnings("unchecked")
    public <T extends Ranking> T getNext(T ranking, String whereClause) {
        if (ranking == null) {
            return null;
        }
        String hql = "FROM " + ranking.getClass().getSimpleName() +
                " WHERE ranking>" + ranking.getRanking();
        if (StringUtils.hasText(whereClause)) {
            hql += " AND " + whereClause;
        }
        hql += ASC_ORDER;
        return (T) HibernateUtil.getFirstItem(hql);
    }

    public Integer add(Ranking ranking) {
        Integer maxRanking = getMaxRanking(ranking.getClass());
        if (IntegerUtils.isPositive(maxRanking)) {
            ranking.setRanking(maxRanking + 1);
        } else {
            ranking.setRanking(1);
        }
        return HibernateUtil.save(ranking);
    }
}
