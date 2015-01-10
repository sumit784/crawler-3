package com.qinyuan15.crawler.core.commodity;

import com.qinyuan15.crawler.dao.Commodity;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test DatabaseCommodityPool
 * Created by qinyuan on 15-1-9.
 */
public class DatabaseCommodityPoolTest {
    @Test
    public void testNext() throws Exception {
        DatabaseCommodityPool pool = new DatabaseCommodityPool();
        int lastId = 0;
        long commodityCount = 0;
        while (true) {
            Commodity commodity = pool.next();
            if (commodity == null) {
                assertThat(commodityCount).isEqualTo(pool.size());
                break;
            }
            commodityCount++;

            assertThat(commodity.getId() > lastId);
            lastId = commodity.getId();
        }
    }
}
