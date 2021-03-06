package com.qinyuan15.crawler.dao;

import com.google.common.collect.Lists;
import com.qinyuan15.crawler.utils.IntegerUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Dao about Ranking Persist Object
 * Created by qinyuan on 15-3-21.
 */
public class RankingDao {

    public final static String ASC_ORDER = " ORDER BY ranking ASC";
    public final static String DESC_ORDER = " ORDER BY ranking DESC";

    public <T extends Ranking> List<T> getInstances(Class<T> clazz) {
        return HibernateUtils.getList(clazz, RankingDao.ASC_ORDER);
    }

    public Integer getMaxRanking(Class<? extends Ranking> clazz) {
        return (Integer) HibernateUtils.getFirstItem("SELECT MAX(ranking) FROM " + clazz.getSimpleName());
    }

    public void switchRanking(Ranking ranking1, Ranking ranking2) {
        if (ranking1 == null || ranking2 == null) {
            return;
        }

        Integer rankingValue1 = ranking1.getRanking();
        Integer rankingValue2 = ranking2.getRanking();

        ranking2.setRanking(-1 * rankingValue2);
        HibernateUtils.update(ranking2);

        ranking1.setRanking(rankingValue2);
        HibernateUtils.update(ranking1);

        ranking2.setRanking(rankingValue1);
        HibernateUtils.update(ranking2);
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
        return (T) HibernateUtils.getFirstItem(hql);
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
        return (T) HibernateUtils.getFirstItem(hql);
    }


    public <T extends Ranking> void rankUp(Class<T> clazz, int id) {
        this.rankUp(clazz, id, "");
    }

    public <T extends Ranking> void rankUp(Class<T> clazz, int id, String limitField) {
        this.rankUp(clazz, id, Lists.newArrayList(limitField));
    }

    public <T extends Ranking> void rankUp(Class<T> clazz, int id, List<String> limitFields) {
        T current = HibernateUtils.get(clazz, id);
        T previous = this.getPrevious(current, getWhereClauseByLimitFields(current, limitFields));
        this.switchRanking(current, previous);
    }

    public <T extends Ranking> void rankDown(Class<T> clazz, int id) {
        this.rankDown(clazz, id, "");
    }

    public <T extends Ranking> void rankDown(Class<T> clazz, int id, String limitField) {
        this.rankDown(clazz, id, Lists.newArrayList(limitField));
    }

    public <T extends Ranking> void rankDown(Class<T> clazz, int id, List<String> limitFields) {
        T current = HibernateUtils.get(clazz, id);
        T next = this.getNext(current, getWhereClauseByLimitFields(current, limitFields));
        this.switchRanking(current, next);
    }

    private String getWhereClauseByLimitFields(Object bean, List<String> limitFields) {
        String whereClause = "";
        for (String limitField : limitFields) {
            if (StringUtils.hasText(limitField)) {
                if (StringUtils.hasText(whereClause)) {
                    whereClause += " AND ";
                }
                whereClause += getLimitFieldCondition(bean, limitField);
            }
        }
        return whereClause;
    }

    private String getLimitFieldCondition(Object bean, String limitField) {
        try {
            Object value = PropertyUtils.getProperty(bean, limitField);
            if (value == null) {
                return limitField + " IS null";
            } else {
                return limitField + "=" + value;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Integer add(Ranking ranking) {
        Integer maxRanking = getMaxRanking(ranking.getClass());
        if (IntegerUtils.isPositive(maxRanking)) {
            ranking.setRanking(maxRanking + 1);
        } else {
            ranking.setRanking(1);
        }
        return HibernateUtils.save(ranking);
    }
}
