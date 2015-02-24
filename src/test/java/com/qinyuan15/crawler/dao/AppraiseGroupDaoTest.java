package com.qinyuan15.crawler.dao;

import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

/**
 * Test AppraiseGroupDao
 * Created by qinyuan on 15-2-24.
 */
public class AppraiseGroupDaoTest {
    private AppraiseGroupDao dao = new AppraiseGroupDao();

    @Test
    public void testGetInstancesByCommodityId() throws Exception {
        assertThat(dao.getInstancesByCommodityId(1)).isNotNull();
    }

    @Test
    public void testGetInstancesByCommodityId1() throws Exception {
        assertThat(dao.getInstancesByCommodityId(1, true)).isNotNull();
        assertThat(dao.getInstancesByCommodityId(1, false)).isNotNull();
    }
}
