package com.qinyuan15.crawler.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Data Access Object of BranchShoppe
 * Created by qinyuan on 15-1-1.
 */
public class BranchShoppeDao {
    private final static Logger LOGGER = LoggerFactory.getLogger(BranchShoppeDao.class);

    public BranchShoppe getInstance(int id) {
        BranchShoppe description = new BranchShoppe();
        description.setName("Adidas");
        description.setLogoUrl("resources/css/images/shoppe/adidas-logo.png");
        description.setDesc("小家具小黑裙小王子，清新风复古风简洁风。<br>" +
                "我们的目标是：逛的不是商品，逛的是情调；<br>" +
                "买的不是淘宝的便宜，买的是省钱的智慧！");
        description.addLinks(BranchShoppeLink.create("javascript:void(0)", "唯品会营店"));
        description.addLinks(BranchShoppeLink.create("javascript:void(0)", "聚美优品官方旗舰店"));
        description.addLinks(BranchShoppeLink.create("javascript:void(0)", "天猫官方旗舰店"));
        description.addLinks(BranchShoppeLink.create("javascript:void(0)", "京东官方旗舰店"));

        return description;
    }

    /*
    public void save(List<Proxy> proxies) {
        Session session = HibernateUtil.getSession();
        try {
            Query query = session.createQuery("FROM Proxy WHERE host=:host and port=:port");
            for (Proxy proxy : proxies) {
                if (query.setString("host", proxy.getHost()).setInteger("port", proxy.getPort())
                        .list().size() == 0) {
                    session.save(proxy);
                    LOGGER.info("save proxy {}.", proxy);
                } else {
                    LOGGER.info("{} already exists, no need to save.", proxy);
                }
            }
        } catch (Exception e) {
            LOGGER.error("fail to save: {}", e);
            throw new RuntimeException(e);
        } finally {
            HibernateUtil.commit(session);
        }
    }
    */
}
