package com.qinyuan15.crawler.dao;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Tool class of PersistObject
 * Created by qinyuan on 15-3-14.
 */
public class PersistObjectUtils {
    private PersistObjectUtils() {
    }

    public static List<Integer> getIds(List<? extends PersistObject> persistObjects) {
        List<Integer> ids = new ArrayList<>();
        if (persistObjects == null) {
            return ids;
        }

        for (PersistObject persistObject : persistObjects) {
            ids.add(persistObject.getId());
        }
        return ids;
    }

    public static String getIdsString(List<? extends PersistObject> categories) {
        List<Integer> ids = getIds(categories);
        return StringUtils.join(ids, ",");
    }

}
