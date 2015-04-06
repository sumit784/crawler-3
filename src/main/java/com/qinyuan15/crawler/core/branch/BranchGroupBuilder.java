package com.qinyuan15.crawler.core.branch;

import com.qinyuan15.crawler.dao.Branch;
import com.qinyuan15.crawler.dao.BranchDao;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class to build BranchGroup
 * Created by qinyuan on 15-4-6.
 */
public class BranchGroupBuilder {

    private final static String NUMBER_LETTER = "0-9";

    private Map<String, List<Branch>> groupByLetter(List<Branch> branches) {
        Map<String, List<Branch>> map = new HashMap<>();

        for (Branch branch : branches) {
            String firstLetter = branch.getFirstLetter();
            if (!StringUtils.hasText(firstLetter)) {
                continue;
            }
            if (firstLetter.matches("^\\d.*$")) {
                firstLetter = NUMBER_LETTER;
            }

            if (!map.containsKey(firstLetter)) {
                map.put(firstLetter, new ArrayList<Branch>());
            }
            map.get(firstLetter).add(branch);
        }

        return map;
    }

    private List<String> getLetters() {
        List<String> letters = new ArrayList<>();
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            letters.add(String.valueOf(ch));
        }
        letters.add(NUMBER_LETTER);
        return letters;
    }


    public List<BranchGroup> build() {
        Map<String, List<Branch>> map = groupByLetter(new BranchDao().getInstances());
        List<BranchGroup> branchGroups = new ArrayList<>();

        for (String letter : getLetters()) {
            List<Branch> branches = map.get(letter);
            if (branches == null) {
                branches = new ArrayList<>();
            }
            branchGroups.add(new BranchGroup(letter, branches));
        }

        return branchGroups;
    }
}
