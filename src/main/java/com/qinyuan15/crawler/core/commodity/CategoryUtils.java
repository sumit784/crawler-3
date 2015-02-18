package com.qinyuan15.crawler.core.commodity;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Tool class about Category
 * Created by qinyuan on 15-2-17.
 */
public class CategoryUtils {
    public static List<String> getCategories() {
        return Lists.newArrayList("女人", "男人", "小孩", "数码家电", "居家");
    }

    public static List<String> getSubCategories(String category) {
        return Lists.newArrayList(
                "女士钱包", "双肩包", "旅行箱", "书包", "单肩包", "百搭单鞋", "女士钱包", "双肩包"
        );
    }
}
