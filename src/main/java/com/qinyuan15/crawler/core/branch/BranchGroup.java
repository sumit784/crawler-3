package com.qinyuan15.crawler.core.branch;

import com.qinyuan15.crawler.dao.Branch;

import java.util.List;

/**
 * Class to group branch
 * Created by qinyuan on 15-2-25.
 */
public class BranchGroup {
    private String letter;
    private List<Branch> branches;

    public BranchGroup(String letter, List<Branch> branches) {
        this.letter = letter;
        this.branches = branches;
    }

    public String getLetter() {
        return letter;
    }

    public List<Branch> getBranches() {
        return branches;
    }


}
