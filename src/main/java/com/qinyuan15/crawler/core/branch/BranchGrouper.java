package com.qinyuan15.crawler.core.branch;

import com.qinyuan15.crawler.dao.Branch;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class to group branch
 * Created by qinyuan on 15-2-25.
 */
public class BranchGrouper {
    public Map<String, List<Branch>> groupByLetter(List<Branch> branches) {
        Map<String, List<Branch>> map = new HashMap<String, List<Branch>>();

        for (Branch branch : branches) {
            String firstLetter = branch.getFirstLetter();
            if (!StringUtils.hasText(firstLetter)) {
                continue;
            }

            if (!map.containsKey(firstLetter)) {
                map.put(firstLetter, new ArrayList<Branch>());
            }
            map.get(firstLetter).add(branch);
        }

        return map;
    }
}
