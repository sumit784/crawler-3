package com.qinyuan15.crawler.dao;

import org.junit.Test;

/**
 * Test IndexLogoDao
 * Created by qinyuan on 15-3-19.
 */
public class IndexLogoDaoTest {
    private IndexLogoDao dao = new IndexLogoDao();

    @Test
    public void testGetInstances() {
        System.out.println(dao.getInstances().size());
    }

    @Test
    public void testGetPrevious() {
        IndexLogo previous = dao.getPrevious(dao.getInstance(6));
        System.out.println(previous == null);
        if (previous != null) {
            System.out.println(previous.getId());
        }

        System.out.println(dao.getPrevious(dao.getInstance(5)) == null);
    }

    @Test
    public void testRankUp() {
        dao.rankUp(5);
        //dao.rankUp(6);
    }

    @Test
    public void testRankDown(){
        dao.rankDown(6);
    }

    @Test
    public void testAdd() {
        //dao.add("hello", "world");
    }
}
