package com.qinyuan15.crawler.dao;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test CommodityDao
 * Created by qinyuan on 15-1-14.
 */
public class CommodityDaoTest {
    @Test
    public void testFactory() throws Exception {
        List<Commodity> commodities = CommodityDao.factory().getInstances();
        assertThat(commodities).isNotEmpty();
    }

    @Test
    public void testInLowPrice() throws Exception {
        List<Commodity> commodities = CommodityDao.factory().setInLowPrice(true).getInstances();
        System.out.println(commodities.size());
    }

    @Test
    public void testSetUserId() throws Exception {
        List<Commodity> commodities = CommodityDao.factory().setUserId(1).getInstances();
        System.out.println(commodities.size());
    }

    @Test
    public void testSetBranchId() throws Exception {
        List<Commodity> commodities = CommodityDao.factory().setBranchId(2).getInstances();
        System.out.println(commodities.size());
        for (Commodity commodity : commodities) {
            assertThat(commodity.getBranchId()).isEqualTo(2);
        }
    }

    @Test
    public void testGetCount() throws Exception {
        CommodityDao.Factory factory = CommodityDao.factory().setBranchId(2);
        assertThat(factory.getInstances()).hasSize((int) (factory.getCount()));
    }

    @Test
    public void testSetKeyWord() throws Exception {
        List<Commodity> commodities = CommodityDao.factory().setKeyWord("兔耳朵").getInstances();
        System.out.println(commodities.size());
        for (Commodity commodity : commodities) {
            assertThat(commodity.getName()).contains("兔耳朵");
        }

        commodities = CommodityDao.factory().setKeyWord("o").getInstances();
        System.out.println(commodities.size());
        for (Commodity commodity : commodities) {
            assertThat(commodity.getName()).contains("o");
        }

        commodities = CommodityDao.factory().setKeyWord("o P").getInstances();
        System.out.println(commodities.size());
        for (Commodity commodity : commodities) {
            assertThat(commodity.getName()).contains("o").contains("P");
        }
    }

    @Test
    public void testSetCategoryId() throws Exception {
        List<Commodity> commodities = CommodityDao.factory().setCategoryId(5).getInstances();
        System.out.println(commodities.size());
    }


    @Test
    public void testGetInstance() throws Exception {
        CommodityDao dao = new CommodityDao();
        Commodity commodity = dao.getInstance(9);
        if (commodity != null) {
            System.out.println(commodity.getActive());
            System.out.println(commodity.getShowId());
            System.out.println(commodity.getSerialNumber());
        }
    }

    @Test
    public void testOrder() throws Exception {
        CommodityDao.Order order = new CommodityDao.Order();

        assertThat(order.toString()).isEqualTo("discoverTime DESC");

        order.setField(CommodityDao.OrderField.PRICE);
        order.setType(CommodityDao.OrderType.ASC);

        assertThat(order.toString()).isEqualTo("price ASC");

        List<Commodity> commodities = CommodityDao.factory().setOrder(order).getInstances();
        Double previousPrice = null;
        for (Commodity commodity : commodities) {
            if (previousPrice != null && commodity.getPrice() != null) {
                assertThat(commodity.getPrice()).isGreaterThanOrEqualTo(previousPrice);
            }
            previousPrice = commodity.getPrice();
        }
    }

    @Test
    public void testGetInstancesByShowId() throws Exception {
        List<Commodity> commodities = new CommodityDao().getInstancesByShowId("40780735321");
        System.out.println(commodities.size());
    }

    @Test
    public void testUpdateSales() throws Exception {
        //new CommodityDao().updateSales(6, 2);
    }

    @Test
    public void testUpdateDiscoverTime() throws Exception {
        //new CommodityDao().updateDiscoverTime(56);
        //new CommodityDao().updateOnShelfTime(6);
    }

    @Test
    public void testUpdateInLowPrice() throws Exception {
        new CommodityDao().updateInLowPrice(41);
    }
}
