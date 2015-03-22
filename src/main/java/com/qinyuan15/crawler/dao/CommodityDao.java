package com.qinyuan15.crawler.dao;

import com.qinyuan15.crawler.core.DateUtils;
import com.qinyuan15.crawler.core.IntegerUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Dao of Commodity
 * Created by qinyuan on 15-1-14.
 */
public class CommodityDao {

    public Commodity getInstance(int id) {
        List<Commodity> commodities = factory().setId(id).getInstances();
        return commodities.size() == 0 ? null : commodities.get(0);
    }

    public String getNameById(Integer id) {
        Commodity commodity = getInstance(id);
        return commodity == null ? null : commodity.getName();
    }

    public List<Commodity> getInstancesByShowId(String showId) {
        Session session = HibernateUtils.getSession();
        String hql = "FROM Commodity WHERE showId=:showId";
        @SuppressWarnings("unchecked")
        List<Commodity> commodities = session.createQuery(hql).setString("showId", showId).list();
        HibernateUtils.commit(session);
        return commodities;
    }

    public void delete(int id) {
        HibernateUtils.delete(Commodity.class, id);
    }

    public void deactivate(int id) {
        Commodity commodity = HibernateUtils.get(Commodity.class, id);
        if (commodity != null) {
            commodity.setActive(false);
            HibernateUtils.update(commodity);
        }
    }

    public void activate(int id) {
        Commodity commodity = HibernateUtils.get(Commodity.class, id);
        if (commodity != null) {
            commodity.setActive(true);
            HibernateUtils.update(commodity);
        }
    }

    public void updateSales(int id, Integer sales) {
        if (sales == null) {
            return;
        }

        Commodity commodity = getInstance(id);
        commodity.setSales(sales);
        HibernateUtils.update(commodity);
    }

    public void updatePrice(int id) {
        Double currentPrice = new CommodityPriceDao().getCurrentPrice(id);
        if (currentPrice != null) {
            Commodity commodity = getInstance(id);
            commodity.setPrice(currentPrice);
            HibernateUtils.update(commodity);
        }
    }

    public void updateOnShelfTime(int id) {
        PriceRecord firstPriceRecord = new PriceRecordDao().getFirstInstance(id);
        if (firstPriceRecord == null) {
            return;
        }

        Commodity commodity = getInstance(id);
        commodity.setOnShelfTime(firstPriceRecord.getRecordTime().toString());
        HibernateUtils.update(commodity);
    }

    public static Factory factory() {
        return new Factory();
    }

    public static enum OrderField {
        ON_SHELF_TIME, PRICE, SALES;

        public static OrderField create(String str) {
            str = str.toLowerCase();
            switch (str) {
                case "price":
                    return PRICE;
                case "sales":
                    return SALES;
                default:
                    return ON_SHELF_TIME;
            }
        }
    }

    public static enum OrderType {
        ASC, DESC;

        public static OrderType create(String str) {
            str = str.toLowerCase();
            if (str.equals("asc")) {
                return ASC;
            } else {
                return DESC;
            }
        }
    }

    public static class Order {
        private OrderField field = OrderField.ON_SHELF_TIME;
        private OrderType type = OrderType.DESC;

        public Order setField(OrderField field) {
            this.field = field;
            return this;
        }

        public Order setType(OrderType type) {
            this.type = type;
            return this;
        }

        @Override
        public String toString() {
            String str;
            switch (this.field) {
                case ON_SHELF_TIME:
                    str = "onShelfTime";
                    break;
                case PRICE:
                    str = "price";
                    break;
                case SALES:
                    str = "sales";
                    break;
                default:
                    str = "onShelfTime";
            }
            switch (this.type) {
                case ASC:
                    str += " ASC";
                    break;
                case DESC:
                    str += " DESC";
                    break;
                default:
                    str += " ASC";
            }
            return str;
        }
    }

    public static class Factory {
        private Integer id;
        private boolean inLowPrice = false;
        private boolean orderByActive = false;
        private Integer categoryId;
        private Boolean active;
        private Integer userId;
        private Integer branchId;
        private String[] keyWords;
        private Order order = new Order();

        public Factory setUserId(Integer userId) {
            this.userId = userId;
            return this;
        }

        public Factory setKeyWord(String keyWord) {
            if (StringUtils.isBlank(keyWord)) {
                this.keyWords = null;
            } else {
                this.keyWords = keyWord.split("\\s+");
            }

            return this;
        }

        public Factory setId(Integer id) {
            this.id = id;
            return this;
        }

        public Factory setActive(Boolean active) {
            this.active = active;
            return this;
        }

        public Factory setBranchId(Integer branchId) {
            this.branchId = branchId;
            return this;
        }

        public Factory setCategoryId(Integer categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public Factory setOrder(Order order) {
            this.order = order;
            return this;
        }

        /**
         * If inLowPrice is set to true, create() only return
         * commodities that in lowest price during three month
         *
         * @param inLowPrice whether get commodities only in lowest price of three month
         */
        public Factory setInLowPrice(boolean inLowPrice) {
            this.inLowPrice = inLowPrice;
            return this;
        }

        public Factory orderByActive() {
            this.orderByActive = true;
            return this;
        }

        private String getHQL() {
            // build SQL query command
            String query = "FROM Commodity WHERE 1=1";

            if (IntegerUtils.isPositive(id)) {
                query += " AND id=" + id;
            }

            if (IntegerUtils.isPositive(categoryId)) {
                String categoryIds = new CategoryDao().getSubInstancesAndSelfIdsString(categoryId);
                query += " AND categoryId IN (" + categoryIds + ")";
            }

            if (IntegerUtils.isPositive(userId)) {
                query += " AND userId=" + userId;
            }

            if (IntegerUtils.isPositive(branchId)) {
                String branchIds = new BranchDao().getAllSubInstancesAndSelfIdsString(branchId);
                query += " AND branchId IN (" + branchIds + ")";
            }

            if (keyWords != null && keyWords.length > 0) {
                for (String keyWord : keyWords) {
                    if (StringUtils.isNotBlank(keyWord)) {
                        query += " AND name LIKE '%" + StringEscapeUtils.escapeSql(keyWord) + "%'";
                    }
                }
            }

            if (active != null) {
                query += " AND active=" + active;
            }

            List<String> orderItems = new ArrayList<>();
            if (this.orderByActive) {
                orderItems.add("active DESC");
            }
            if (this.order != null) {
                orderItems.add(this.order.toString());
            }
            if (orderItems.size() > 0) {
                query += " ORDER BY " + StringUtils.join(orderItems, ",");
            }
            return query;
        }

        public long getCount() {
            @SuppressWarnings("unchecked")
            List<Long> list = HibernateUtils.getList("SELECT COUNT(*) " + getHQL());
            return list.get(0);
        }

        public List<Commodity> getInstances() {
            @SuppressWarnings("unchecked")
            List<Commodity> commodities = HibernateUtils.getList(getHQL());

            if (!inLowPrice) {
                return commodities;
            } else {
                List<Commodity> commoditiesInLowPrice = new ArrayList<>();
                String startTime = DateUtils.threeMonthAgo().toString();
                String endTime = DateUtils.now().toString();
                for (Commodity commodity : commodities) {
                    Integer commodityId = commodity.getId();
                    Double lowPrice = CommodityPriceDao.range(commodityId)
                            .setStartTime(startTime).setEndTime(endTime).getMin();
                    Double currentPrice = new CommodityPriceDao().getCurrentPrice(commodityId);
                    if (currentPrice - lowPrice <= 0.01) {
                        commoditiesInLowPrice.add(commodity);
                    }
                }
                return commoditiesInLowPrice;
            }
        }
    }
}
