package com.qinyuan15.crawler.dao;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to query/create SimpleCommodity
 * Created by qinyuan on 14-12-27.
 */
public class SimpleCommodityFactory /*extends AbstractDao<SimpleCommodity>*/ {

    private int branchId;
    private int categoryId;
    private String[] keyWords;

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setKeyWord(String keyWord) {
        this.keyWords = keyWord.split("\\s+");
    }

    private SimpleCommodity mockSimpleCommodity(int id, String name, double price, int branchId, int categoryId) {
        SimpleCommodity commodity = new SimpleCommodity();
        commodity.setId(id);
        commodity.setName(name);
        commodity.setPrice(price);
        commodity.setBranch(new BranchFactory().getInstance(branchId));
        commodity.setCategoryId(categoryId);
        return commodity;
    }

    private List<SimpleCommodity> getAllInstances() {
        List<SimpleCommodity> list = new ArrayList<SimpleCommodity>();
        list.add(mockSimpleCommodity(1, "原创春夏季女装 文艺田园清新宽松娃娃领纯绵双层纱T恤上衣常规", 89.80, 1, 1));
        list.add(mockSimpleCommodity(2, "冬装特卖 真维斯女装舒适修身时尚波点棉衣外套", 189.70, 4, 1));
        list.add(mockSimpleCommodity(3, "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", 179.70, 4, 1));
        list.add(mockSimpleCommodity(4, "YYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY", 289.70, 4, 1));
        list.add(mockSimpleCommodity(5, "ZZZZZZZZZZZZZZZZZZZZZzZZZZZYYYYYYYYY", 389.70, 4, 1));
        list.add(mockSimpleCommodity(6, "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", 389.70, 4, 2));
        list.add(mockSimpleCommodity(7, "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB", 389.70, 4, 2));
        return list;
    }

    //@Override
    public List<SimpleCommodity> getInstances() {
        List<SimpleCommodity> list = getAllInstances();
        List<SimpleCommodity> filteredList = new ArrayList<SimpleCommodity>();
        for (SimpleCommodity commodity : list) {
            if (branchId > 0 && commodity.getBranch().getId() != branchId) {
                continue;
            }
            if (categoryId > 0 && commodity.getCategoryId() != categoryId) {
                continue;
            }
            if (keyWords != null && !containsKeyWords(commodity)) {
                continue;
            }

            filteredList.add(commodity);
        }

        return filteredList;
    }

    private boolean containsKeyWords(SimpleCommodity commodity) {
        String name = commodity.getName();
        for (String keyWord : keyWords) {
            if (StringUtils.hasText(keyWord) && name.contains(keyWord)) {
                return true;
            }
        }
        return false;
    }
}
