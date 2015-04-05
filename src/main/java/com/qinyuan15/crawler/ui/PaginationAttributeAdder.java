package com.qinyuan15.crawler.ui;

import com.qinyuan15.crawler.dao.PaginationFactory;
import com.qinyuan15.crawler.utils.IntegerUtils;
import org.springframework.ui.ModelMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class to add attributes about pagination to ModelMap
 * Created by qinyuan on 15-4-5.
 */
public class PaginationAttributeAdder {
    public final static int DEFAULT_PAGE_SIZE = 50;
    public final static int DEFAULT_VISIBLE_BUTTON_SIZE = 5;

    private Integer pageNumber;
    private PaginationFactory factory;
    private int pageSize = DEFAULT_PAGE_SIZE;
    private int visibleButtonSize = DEFAULT_VISIBLE_BUTTON_SIZE;

    private Map<String, Object> requestParameters = new HashMap<>();

    public PaginationAttributeAdder(PaginationFactory factory, Integer pageNumber) {
        this.pageNumber = pageNumber;
        this.factory = factory;
    }

    public PaginationAttributeAdder addRequestParameter(String key, Object value) {
        requestParameters.put(key, value);
        return this;
    }

    public void addAttributes(String pageUrl, String instancesKey, ModelMap model) {
        if (!IntegerUtils.isPositive(pageNumber)) {
            pageNumber = 1;
        }

        long itemCount = factory.getCount();
        int pageCount = PaginationUtils.getPageCount(itemCount, pageSize);
        if (pageNumber > pageCount) {
            pageNumber = pageCount;
        }

        if (requestParameters.size() > 0) {
            if (!pageUrl.endsWith("?")) {
                pageUrl += "?";
            }
            for (Map.Entry entry : requestParameters.entrySet()) {
                if (entry.getValue() != null) {
                    pageUrl += "&" + entry.getKey() + "=" + entry.getValue();
                }
            }
        }

        List<PaginationAnchor> anchors = PaginationAnchor.create(
                pageUrl, pageCount, visibleButtonSize, pageNumber);

        model.addAttribute("paginationAnchors", anchors);
        model.addAttribute("rowStartIndex", (pageNumber - 1) * pageSize + 1);
        model.addAttribute(instancesKey, factory.getInstances((pageNumber - 1) * pageSize, pageSize));
    }
}
