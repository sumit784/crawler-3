package com.qinyuan15.crawler.core.category;

import com.qinyuan15.crawler.dao.Category;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Tool class of Category
 * Created by qinyuan on 15-3-13.
 */
public class CategoryUtils {
    private CategoryUtils() {
    }

    public static List<Integer> getIds(List<Category> categories) {
        List<Integer> ids = new ArrayList<Integer>();
        if (categories == null) {
            return ids;
        }

        for (Category category : categories) {
            ids.add(category.getId());
        }
        return ids;
    }

    public static String getIdsString(List<Category> categories) {
        List<Integer> ids = getIds(categories);
        return StringUtils.join(ids, ",");
    }
}
